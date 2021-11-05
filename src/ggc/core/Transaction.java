package ggc.core;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private int _id;
    private Date _paymentDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;
    private boolean _isPaid;

    Transaction(Product product, int quantity, Partner partner) {
        _product = product;
        _quantity = quantity;
        _partner = partner;
    }

    int getId() {
        return _id;
    }
    
    Partner getPartner() {
        return _partner;
    }

    Product getProduct() {
        return _product;
    }

    int getQuantity() {
        return _quantity;
    }

    double getBaseValue() {
        return _baseValue;
    }

    Date getPaymentDate() {
        return _paymentDate;
    }

    public boolean isPaid() {
        return _isPaid;
    }
    
}
