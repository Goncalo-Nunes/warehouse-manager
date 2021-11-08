package ggc.core;

import java.util.ArrayList;
import java.util.List;

public class BreakdownSale extends Sale {

    private List<Batch> _batches = new ArrayList<Batch>();

    BreakdownSale(Product product, int quantity, Partner partner) {
        super(product, quantity, partner);
    }


    public String toString() {
        Partner partner = getPartner();
        AggregateProduct product = (AggregateProduct)getProduct();
        Recipe recipe = product.getRecipe();
        double baseValue = getBaseValue();
        double payed = 0;

        if(baseValue > 0) {
            payed = baseValue;
        }

        return "DESAGREGAÇÂO" + "|" + getId() + "|" + partner.getId() + 
        "|" + product.getId() + "|" + getQuantity() + "|" + baseValue
        + "|" + payed + "|" + getPaymentDate() + "|" + recipe.toString();
    }
}
