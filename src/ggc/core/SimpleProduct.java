package ggc.core;


public class SimpleProduct extends Product {
    private final int N = 5;

    SimpleProduct(String id) {
        super(id);
    }

    int getN() {
        return N;
    }

    void checkQuantity(int quantity, Partner partner) {

    }
}
