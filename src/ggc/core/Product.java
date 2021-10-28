package ggc.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * This is an abstract class representing a Warehouse Product. All products
 * have an unique ID, a total stock, its price, and a set of batches.
 */
public abstract class Product implements Serializable {
   
    /** Product's max price. */
    private double _maxPrice;

    /** Product's total stock. */
    private int _totalStock;

    /** Product's unique ID. */
    private String _id;

    /** Product's list of batches. */
    private List<Batch> _batches = new LinkedList<Batch>();


    /**
     * Create a product.
     * 
     * @param id
     *          product ID.
     */
    Product(String id) {
        _maxPrice = 0;
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
    List<Batch> getBatches() {
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
        }

        _batches.add(new Batch(price, quantity, partner, this));
        _totalStock += quantity;
    }
    
}
