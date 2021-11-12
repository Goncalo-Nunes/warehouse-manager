package ggc.core;

public class Acquisition extends Transaction {
    Acquisition(int id, Product product, int quantity, Partner partner) {
        super(id, product, quantity, partner);
        setPaid(true);
    }

    Acquisition(int id, Product product, int quantity, Partner partner, double price) {
        super(id, product, quantity, partner);
        setBaseValue(price * quantity);
    }

    @Override
    void pay() {}

    double getTotalValue() {
        return 0;
    }

    public String toString() {
        Partner partner = getPartner();
        Product product = getProduct();
        return "COMPRA|" + getId() + "|" + partner.getId() + "|" + product.getId() + "|" + getQuantity()
         + "|" + Math.round(getBaseValue()) + "|" + getPaymentDate();
    }
}
