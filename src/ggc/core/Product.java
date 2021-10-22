package ggc.core;

import java.util.LinkedList;

public abstract class Product {
    private double _maxPrice;
    private String _id;
    private LinkedList<Batch> _batches;

    Product(String id) {
        _maxPrice = 0;
        _id = id;
    }

    public String toString() {
        
    }

    abstract void checkQuantity(int quantity, Partner partner);

    public void addBatch(double price, int quantity, Partner partner) {
        _batches.add(new Batch(price, quantity, partner, this));
    }
    
}
