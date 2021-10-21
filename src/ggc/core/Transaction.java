package ggc.core;

public abstract class Transaction {
    private int _id;
    private Date _paymentDate;
    private double _baseValue;
    private int _quantity;
    private Product _product;
    private Partner _partner;

    Transaction(Product product, int quantity, Partner partner) {
        _product = product;
        _quantity = quantity;
        _partner = partner;
    }

    public boolean isPaid() {

    }

    public Date getPaymentDate() {
        return _paymentDate;
    }
}
