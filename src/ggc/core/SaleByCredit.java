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

    public String toString() {
        Partner partner = getPartner();
        Product product = getProduct();
        String ret =  "VENDA|" + "|" + getId() + "|" + partner.getId() + "|" + product.getId()
        + "|" + getQuantity() + "|" + getBaseValue() + "|" + _amountPaid + "|" + _deadline;

        if(isPaid()) {
            ret += "|" + getPaymentDate();
        }

        return ret;
    }
}
