package ggc.core;

public abstract class Sale extends Transaction {
    Sale(int id, Product product, int quantity, Partner partner) {
        super(id, product, quantity, partner);
    }
}
