package ggc.core;

import java.io.Serializable;



/**
 * This is a class representing a Warehouse Product Batch. All batches
 * have a product associated, as long as a price, quantity and partner.
 */
public class Batch implements Serializable {

    /** Batch's price. */
    private double _price;

    /** Batch's stock. */
    private int _quantity;

    /** Batch's product. */
    private Product _product;

    /** Partner associated to the batch. */
    private Partner _partner;


    /**
     * Create a batch.
     * 
     * @param id
     *          batch ID.
     */
    Batch(double price, int quantity, Partner partner, Product product) {
        _price = price;
        _quantity = quantity;
        _product = product;
        _partner = partner;
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    public String toString() {
        return _product.getId() + "|" + _partner.getId() + "|" + Math.round(_price) + "|" + _quantity;
    }

    /**
     * @return the batch's product.
     */
    Product getProduct() {
        return _product;
    }

    /**
     * @return the batch's partner.
     */
    Partner getPartner() {
        return _partner;
    }


    /**
     * @return the batch's price.
    */
    double getPrice() {
        return _price;
    }


    /**
     * @return the batch's stock.
    */
    int getQuantity() {
        return _quantity;
    }
}
