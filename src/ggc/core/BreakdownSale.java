package ggc.core;

import java.util.ArrayList;

public class BreakdownSale extends Sale {

    private ArrayList<Batch> _batches;

    BreakdownSale(Product product, int quantity, Partner partner) {
        super(product, quantity, partner);
    }

    /*
    public String toString() {

    }
    */
}
