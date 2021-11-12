package ggc.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;


/**
 * This is an abstract class representing a Warehouse Product. All products
 * have an unique ID, a total stock, its price, and a set of batches.
 */
public abstract class Product implements ObservableProduct {
   
    /** Product's max price. */
    private double _maxPrice;

    private double _allTimeHigh;

    /** Product's max price. */
    private double _minPrice;

    /** Product's total stock. */
    private int _totalStock;

    /** Product's unique ID. */
    private String _id;

    /** Product's list of batches. */
    private Set<Batch> _batches = new TreeSet<Batch>(new BatchComparator());

    /** Array containing observers who want to be notified about this product's events. */
    private List<ProductObserver> _observers = new ArrayList<ProductObserver>();

    /**
     * Create a product.
     * 
     * @param id
     *          product ID.
     */
    Product(String id) {
        _maxPrice = Double.MIN_VALUE;;
        _allTimeHigh = 0;
        _minPrice = Double.MAX_VALUE;
        _totalStock = 0;
        _id = id;
    }

    /**
     * @return the product's ID.
     */
    String getId() {
        return _id;
    }

    /**
     * @return the product's price.
     */
    double getPrice() {
        calculateMaxPrice();
        return _maxPrice;
    }

    /**
     * @return the product's batches.
     */
    Set<Batch> getBatches() {
        return _batches;
    }

    int getTotalStock() {
        return _totalStock;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    public String toString() {
        return "" + _id + "|" + Math.round(_allTimeHigh) + "|" + _totalStock;
    }

    Recipe getRecipe() {
        return null;
    }

    Double getMinPrice() {
        calculateMinPrice();
        return _minPrice;
    }

    abstract int getN();

    /**
     * Adds a new batch
     * 
     * @param price
     *          the batches' price.
     * @param quantity
     *          the stock of the batch
     * @param partner
     *          partner associated with the batch
     */
    void addBatch(double price, int quantity, Partner partner) {
        if(price > getPrice()) {
            _maxPrice = price;
        } else if (price < getMinPrice()) {

            if(_totalStock != 0) {
                notifyObservers("BARGAIN", price);
            }
            _minPrice = price;
        }

        Batch batch = new Batch(price, quantity, partner, this);
        partner.addBatch(batch);
        _batches.add(batch);
        addStock(quantity, batch);

        if(price > _allTimeHigh) {
            _allTimeHigh = price;
        }
    }

    double getAllTimeHigh() {
        return _allTimeHigh;
    }

    void calculateMinPrice() {
        Double min = Double.MAX_VALUE;

        for(Batch batch : _batches) {
            if(batch.getPrice() < min) {
                min = batch.getPrice();
            }
        }

        _minPrice = min;
    }

    void calculateMaxPrice() {
        Double max = Double.MIN_VALUE;

        for(Batch batch : _batches) {
            if(batch.getPrice() > max) {
                max = batch.getPrice();
            }
        }

        _maxPrice = max;
    }

    void removeBatch(Batch batch) {
        _batches.remove(batch);
        batch.getPartner().removeBatch(batch);
        removeStock(batch.getQuantity());

        if (batch.getPrice() == _minPrice) {
            calculateMinPrice();
        } else if (batch.getPrice() == _maxPrice) {
            calculateMaxPrice();
        } 
    }

    void addStock(int quantity, Batch batch) {
        if(_totalStock == 0 && quantity > 0 && batch.getProduct().getAllTimeHigh() != 0) {
            notifyObservers("NEW", batch.getPrice());
        }

        _totalStock += quantity;
    }

    void removeStock(int quantity) {
        _totalStock -= quantity;
    }


    boolean observerExists(ProductObserver observer) {
        return _observers.contains(observer);
    }

    @Override
    public void registerObserver(ProductObserver observer) {
        _observers.add(observer);
    }

    @Override
    public void removeObserver(ProductObserver observer) {
        _observers.remove(observer);
    }

    @Override
    public void notifyObservers(String type, double price) {
        for (ProductObserver observer : _observers) {
            observer.update(type, this, price);
        }
    }
    
}
