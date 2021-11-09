package ggc.core;

import java.io.Serializable;
import java.util.HashMap;
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

    /** Product's max price. */
    private double _minPrice;

    /** Product's total stock. */
    private int _totalStock;

    /** Product's unique ID. */
    private String _id;

    /** Product's list of batches. */
    private Set<Batch> _batches = new TreeSet<Batch>(new BatchComparator());

    /** Array containing observers who want to be notified about this product's events. */
    private Map<ProductObserver, Boolean> _observers = new HashMap<ProductObserver, Boolean>();

    /**
     * Create a product.
     * 
     * @param id
     *          product ID.
     */
    Product(String id) {
        _maxPrice = 0;
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


    /**
	 * @see java.lang.Object#toString()
	 */
    public String toString() {
        return "" + _id + "|" + Math.round(_maxPrice) + "|" + _totalStock;
    }

    /**
	 * @param quantity
     *      quantity to check if available
     * @param partner
     *      partner to check
	 */
    abstract void checkQuantity(int quantity, Partner partner);


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
            notifyObservers("BARGAIN");
        }

        _batches.add(new Batch(price, quantity, partner, this));
        addStock(quantity);
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

    @Override
    public void registerObserver(ProductObserver observer) {
        _observers.put(observer, true);
    }

    @Override
    public void removeObserver(ProductObserver observer) {
        _observers.put(observer, true);
    }

    @Override
    public void notifyObservers(String type) {
        for (Map.Entry<ProductObserver, Boolean> observer : _observers.entrySet()) {
            if (observer.getValue() == true) {
                observer.getKey().update(type, this);
            }
        }
    }
    
}
