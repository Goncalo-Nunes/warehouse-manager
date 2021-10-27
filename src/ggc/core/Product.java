package ggc.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class Product implements Serializable {
    private double _maxPrice;
    private int _totalStock;
    private String _id;
    private List<Batch> _batches = new LinkedList<Batch>();

    Product(String id) {
        _maxPrice = 0;
        _totalStock = 0;
        _id = id;
    }


    public String getId() {
        return _id;
    }

    public double getPrice() {
        return _maxPrice;
    }

    public List<Batch> getBatches() {
        return _batches;
    }

    public String toString() {
        return "" + _id + "|" + Math.round(_maxPrice) + "|" + _totalStock;
    }

    abstract void checkQuantity(int quantity, Partner partner);

    public void addBatch(double price, int quantity, Partner partner) {
        if(price > _maxPrice) {
            _maxPrice = price;
        }

        _batches.add(new Batch(price, quantity, partner, this));
        _totalStock += quantity;
    }
    
}
