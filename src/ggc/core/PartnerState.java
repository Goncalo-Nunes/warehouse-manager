package ggc.core;

import java.io.Serializable;

public abstract class PartnerState implements Serializable {
        
        private static final long serialVersionUID = 20201985673L;
    
        
        private Partner _partner;
    

        PartnerState(Partner partner) {
            _partner = partner;
        }
    
        public Partner getPartner() {
            return _partner;
        }
        
        double p1Modifier() {
            return 0.9;
        }
    

        abstract double p2Modifier(int paymentGap);
    

        abstract double p3Modifier(int paymentGap);
    

        abstract double p4Modifier(int paymentGap);
    
        abstract void pay(SaleByCredit sale);
}
