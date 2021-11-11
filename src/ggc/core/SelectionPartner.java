package ggc.core;

public class SelectionPartner extends PartnerState {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202012040059L;
    

    public SelectionPartner(Partner partner) {
        super(partner);
    }


    @Override
    public double p2Modifier(int paymentGap) {
        if(paymentGap >= 2) {
            return 0.95;
        }

        return 1.0;
    }


    @Override
    public double p3Modifier(int paymentGap) {
        if (paymentGap <= -1) {
            return 1.0;
        }

        return 1.0 + 0.02 * (-paymentGap);
    }


    @Override
    public double p4Modifier(int paymentGap) {
        return 1.0 + 0.05 * (-paymentGap);
    }


    @Override
    public void pay(SaleByCredit sale) {
        Partner partner = getPartner();
        int paymentGap = sale.getLimitDateGap();

        if (paymentGap < -2) {
            partner.setPoints(partner.getPoints() / 10);
            partner.setStatus(new NormalPartner(partner));
            return;
        }
        if (paymentGap >= 0) {
            partner.setPoints(partner.getPoints() + 10 * sale.getTotalValue());
        }
        if (partner.getPoints() > 25000) {
            partner.setStatus(new ElitePartner(partner));
        }
    }


    @Override
    public String toString() {
        return "SELECTION";
    }

}
