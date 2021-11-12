package ggc.core;
import java.io.Serializable;

public class Notification implements Serializable {
    private String _type;
    private Product _product;
    private double _price;

    public Notification(String type, Product product, double price) {
        _type = type;
        _product = product;
        _price = price;
    }

    public String toString() {
        return _type + "|" + _product.getId() + "|" + Math.round(_price);

    }
}
