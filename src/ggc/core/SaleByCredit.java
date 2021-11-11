package ggc.core;

public class SaleByCredit extends Sale {
    private Date _deadline;
    private double _amountPaid;

    SaleByCredit(Product product, int quantity, Partner partner, int deadline) {
        super(product, quantity, partner);
        _deadline = new Date(deadline);
    }

    double getAmountPaid() {
        return _amountPaid;
    }

    Date getDeadLine() {
        return _deadline;
    }

    int getLimitDateGap() {
        return _deadline.difference(getCurrentDate());
    }

    double getTotalValue() {
        if(isPaid()) {
            return _amountPaid;
        }

        PartnerState status = getPartner().getStatus();
        int N = getProduct().getN();
        int paymentGap = getLimitDateGap(); 
        if (paymentGap >= 0) {
            if (paymentGap >= N) {
                return getBaseValue() * status.p1Modifier();
            } else {
                return getBaseValue() * status.p2Modifier(paymentGap);
            }
        } else {
            paymentGap = -paymentGap;
            if (0 < paymentGap && paymentGap <= N) {
                return getBaseValue() * status.p3Modifier(-paymentGap);
            } else {
                return getBaseValue() * status.p4Modifier(-paymentGap);
            }
        }
    }

    void pay() {
        _amountPaid = getTotalValue();
        setPaymentDate(getCurrentDate());
        getPartner().paySale(this);
    }


    public String toString() {
        Partner partner = getPartner();
        Product product = getProduct();
        String ret =  "VENDA|" + "|" + getId() + "|" + partner.getId() + "|" + product.getId()
        + "|" + getQuantity() + "|" + getBaseValue() + "|" + Math.round(_amountPaid) + "|" + _deadline;

        if(isPaid()) {
            ret += "|" + getPaymentDate();
        }

        return ret;
    }
}
