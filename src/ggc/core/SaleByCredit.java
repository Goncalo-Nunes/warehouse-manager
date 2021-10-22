package ggc.core;

public class SaleByCredit extends Sale {
    private Date _deadline;
    private double amountPaid;

    SaleByCredit(Product product, int quantity, Partner partner, int deadline) {
        super(product, quantity, partner);
        _deadline = new Date(deadline);
    }

    /*
    public String toString() {

    }
    */
}
