package ggc.core;

import java.util.ArrayList;
import java.util.List;

public class BreakdownSale extends Sale {

    private List<Batch> _batches = new ArrayList<Batch>();

    BreakdownSale(Product product, int quantity, Partner partner) {
        super(product, quantity, partner);
    }


    //FIXME falta o resto
    public String toString() {
        Partner partner = getPartner();
        Product product = getProduct();
        return "DESAGREGAÇÂO" + "|" + getId() + "|" + partner.getId();
    }
}
