package ggc.core;

import java.util.Comparator;

public class BatchComparator implements Comparator<Batch> {
    public int compare(Batch b1, Batch b2) {
        int diff = b1.getProduct().getId().compareToIgnoreCase(b2.getProduct().getId());
        if (diff != 0) {
            return diff;
        }

        diff = b1.getPartner().getId().compareToIgnoreCase(b2.getPartner().getId());
        if (diff != 0) {
            return diff;
        }

        diff = (int)(b1.getPrice() - b2.getPrice());
        if (diff != 0) {
            return diff;
        }

        return (int)(b1.getQuantity() - b2.getQuantity()); 
    }
    
}
