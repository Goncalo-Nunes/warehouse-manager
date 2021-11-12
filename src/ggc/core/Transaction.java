package ggc.core;

import java.io.Serializable;

import javax.swing.AbstractAction;

public abstract class Transaction implements Serializable {
    private int _id;
    private Date _paymentDate;
    private Date _warehouseDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;
    private boolean _isPaid;

    Transaction(int id, Product product, int quantity, Partner partner) {
        _id = id;
        _product = product;
        _quantity = quantity;
        _partner = partner;
    }

    double getAmountPaid() {
        return 0;
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

    void setPaymentDate(Date date) {
        _paymentDate = date;
    }

    Date  getCurrentDate() {
        return _warehouseDate;
    }

    void setCurrentDate(Date date) {
        _warehouseDate = date;
    }

    void setBaseValue(double price) {
        _baseValue = price;
    }


    abstract void pay();

    abstract double getTotalValue();
    
    boolean isPaid() {
        return _isPaid;
    }

    void setPaid(boolean isPaid) {
        _isPaid = isPaid;
    }
    
}
