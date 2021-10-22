package ggc.core;

public class Notification {
    private String _type; //Enum
    private Product _product;

    public String toString() {
        return _type + "|" + _product.getId() + "|" + _product.getPrice();

    }
}
