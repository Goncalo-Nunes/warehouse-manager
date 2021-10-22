package ggc.core;

public class AggregateProduct extends Product{
    
    private Recipe _recipe;

    AggregateProduct(String id) {
        super(id);
    }

    public void setRecipe(Recipe recipe) {
        _recipe = recipe;
    }

    void checkQuantity(int quantity, Partner partner) {

    }

    public String toString() {
        return super.toString() + "|" + _recipe.toString();
    }
}
