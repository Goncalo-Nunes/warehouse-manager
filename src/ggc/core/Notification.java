package ggc.core;
import java.io.Serializable;

public class Notification implements Serializable {
    private String _type; //Enum
    private Product _product;

    public String toString() {
        return _type + "|" + _product.getId() + "|" + _product.getPrice();

    }
}
