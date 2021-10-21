package ggc.core;

public class Acquisition extends Transaction {
    Acquisition(Product product, int quantity, Partner partner) {
        super(product, quantity, partner);
    }
}
