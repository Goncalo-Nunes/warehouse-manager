package ggc.core;

public class Batch {
    private double _price;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Batch(double price, int quantity, Partner partner, Product product) {
        _price = price;
        _quantity = quantity;
        _product = product;
        _partner = partner;
    }

    public String toString() {
        return _product.getId() + "|" + _partner.getId() + "|" + _price + "|" + _quantity;
    }

    /*
    Batch makeCopy() {

    }
    */

    public Product getProduct() {
        return _product;
    }

    public Partner getPartner() {
        return _partner;
    }

    public double getPrice() {
        return _price;
    }

    public int getQuantity() {
        return _quantity;
    }
}
