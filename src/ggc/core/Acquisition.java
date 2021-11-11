package ggc.core;

public class Acquisition extends Transaction {
    Acquisition(Product product, int quantity, Partner partner) {
        super(product, quantity, partner);
    }

    Acquisition(Product product, int quantity, Partner partner, double price) {
        super(product, quantity, partner);
        setBaseValue(price);
    }

    public String toString() {
        Partner partner = getPartner();
        Product product = getProduct();
        return "COMPRA|" + getId() + "|" + partner.getId() + "|" + product.getId()
         + "|" + getBaseValue() + "|" + getPaymentDate();
    }
}
