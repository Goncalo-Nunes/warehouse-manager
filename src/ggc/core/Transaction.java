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

    
    public boolean isPaid() {
        return _isPaid;
    }
    
    double getBaseValue() {
        return _baseValue;
    }

    Date getPaymentDate() {
        return _paymentDate;
    }
}
