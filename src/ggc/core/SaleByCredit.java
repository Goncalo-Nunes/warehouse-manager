package ggc.core;

public class SaleByCredit extends Sale {
    private Date _deadline;
    private double _amountPaid;

    SaleByCredit(Product product, int quantity, Partner partner, int deadline) {
        super(product, quantity, partner);
        _deadline = new Date(deadline);
    }

    public Double getAmountPaid() {
        return _amountPaid;
    }

    /*
    public String toString() {

    }
    */
}
