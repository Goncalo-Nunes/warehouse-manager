package ggc.core;

public interface ProductObserver {

    void update(String type, Product product, double price);

    int hashCode();

    boolean equals(Object other);
}
