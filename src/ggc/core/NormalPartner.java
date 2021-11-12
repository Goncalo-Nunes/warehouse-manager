package ggc.core;

public class NormalPartner extends PartnerState {
    private static final long serialVersionUID = 202109091821L;
    

    public NormalPartner(Partner partner) {
        super(partner);
    }


    @Override
    public double p2Modifier(int paymentGap) {
        return 1.0;
    }


    @Override
    public double p3Modifier(int paymentGap) {
        return 1.0 + 0.05 * (-paymentGap);
    }


    @Override
    public double p4Modifier(int paymentGap) {
        return 1.0 + 0.10 * (-paymentGap);
    }


    @Override
    public void pay(SaleByCredit sale) {
        Partner partner = getPartner();
        int paymentGap = sale.getLimitDateGap();

        if(paymentGap >= 0) {
            partner.setPoints(partner.getPoints() + 10 * sale.getTotalValue());
        } else {
            partner.setPoints(0);
            return;
        }

        if(partner.getPoints() > 25000) {
            partner.setStatus(new ElitePartner(partner));
        } else if(partner.getPoints() > 2000) {
            partner.setStatus(new SelectionPartner(partner));
        }
    }


    @Override
    public String toString() {
        return "NORMAL";
    }
}
