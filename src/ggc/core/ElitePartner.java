package ggc.core;

public class ElitePartner extends PartnerState {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202012040059L;
    

    public ElitePartner(Partner partner) {
        super(partner);
    }


    @Override
    public double p2Modifier(int paymentGap) {
        return 0.9;
    }


    @Override
    public double p3Modifier(int paymentGap) {
        return 0.95;
    }


    @Override
    public double p4Modifier(int paymentGap) {
        return 1.0;
    }


    @Override
    public void pay(SaleByCredit sale) {
        Partner partner = getPartner();
        int paymentGap = sale.getLimitDateGap();

        if (paymentGap >= 0) {
            partner.setPoints(partner.getPoints() + 10 * sale.getTotalValue());
        } else if (paymentGap < -15) {
            partner.setStatus(new SelectionPartner(partner));
            partner.setPoints(partner.getPoints() / 4);
        }
    }


    @Override
    public String toString() {
        return "ELITE";
    }

}
