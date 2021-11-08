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
        AggregateProduct product = (AggregateProduct)getProduct();
        Recipe recipe = product.getRecipe();
        return "DESAGREGAÇÂO" + "|" + getId() + "|" + partner.getId() + 
        "|" + product.getId() + "|" + getQuantity() + "|" + recipe.getAlpha()
        + "|" + getBaseValue() + "|" + getPaymentDate() + "|" + recipe.toString();
    }
}
