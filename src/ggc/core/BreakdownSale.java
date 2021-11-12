package ggc.core;

import java.util.ArrayList;
import java.util.List;

public class BreakdownSale extends Sale {

    private List<Batch> _batches = new ArrayList<Batch>();


    BreakdownSale(int id, Product product, int quantity, Partner partner) {
        super(id, product, quantity, partner);
        setPaid(true);
    }

    @Override
    void pay() {}

    double getTotalValue() {
        return 0;
    }

    double getAmountPaid() {
        if(getBaseValue() < 0) {
            return 0;
        }

        return getBaseValue();
    }

    void setBatches(List<Batch> batches) {
        _batches = batches;
    }

    public String toString() {
        Partner partner = getPartner();
        AggregateProduct product = (AggregateProduct)getProduct();

        String recipeString = "";
        for(Batch batch : _batches) {
            recipeString += batch.getProduct().getId() + ":" + Math.round(batch.getQuantity()) + ":" + Math.round(batch.getPrice()) + "#";
        }
        recipeString = recipeString.substring(0, recipeString.length()-1);


        return "DESAGREGAÇÃO" + "|" + getId() + "|" + partner.getId() + 
        "|" + product.getId() + "|" + getQuantity() + "|" + Math.round(getBaseValue())
        + "|" + Math.round(getAmountPaid()) + "|" + getPaymentDate() + "|" + recipeString;
    }
}
