package ggc.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
    private Set<ProductObserver> _observers = new HashSet<ProductObserver>();

    /**
     * Create a product.
     * 
     * @param id
     *          product ID.
     */
    Product(String id) {
        _maxPrice = 0;
        _allTimeHigh = 0;
        _minPrice = 0;
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
        return "" + _id + "|" + Math.round(_maxPrice) + "|" + _totalStock;
    }

    Recipe getRecipe() {
        return null;
    }

    Double getMinPrice() {
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
        if(price > _maxPrice) {
            _maxPrice = price;
        } else if (price < _minPrice) {
            _minPrice = price;

            if(_totalStock != 0) {
                notifyObservers("BARGAIN");
            }
        }

        if(price > _allTimeHigh) {
            _allTimeHigh = price;
        }

        Batch batch = new Batch(price, quantity, partner, this);
        partner.addBatch(batch);
        _batches.add(batch);
        addStock(quantity);
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

    void addStock(int quantity) {
        if(_totalStock == 0 && quantity > 0) {
            notifyObservers("NEW");
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
    public void notifyObservers(String type) {
        for (ProductObserver observer : _observers) {
            observer.update(type, this);
        }
    }
    
}
