package ggc.core;

import java.util.LinkedList;

public abstract class Product {
    private double _maxPrice;
    private int _totalStock;
    private String _id;
    private LinkedList<Batch> _batches;

    Product(String id) {
        _maxPrice = 0;
        _id = id;
    }


    public String getId() {
        return _id;
    }

    public double getPrice() {
        return _maxPrice;
    }

    public String toString() {
        return "" + _id + "|" + _maxPrice + "|" + _totalStock;
    }

    abstract void checkQuantity(int quantity, Partner partner);

    public void addBatch(double price, int quantity, Partner partner) {
        _batches.add(new Batch(price, quantity, partner, this));
    }
    
}
