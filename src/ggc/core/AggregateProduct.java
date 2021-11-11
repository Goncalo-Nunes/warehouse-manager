package ggc.core;

public class AggregateProduct extends Product{
    
    private final int N = 5;
    private Recipe _recipe;
    

    AggregateProduct(String id) {
        super(id);
    }

    int getN() {
        return N;
    }

    void setRecipe(Recipe recipe) {
        _recipe = recipe;
    }

    Recipe getRecipe() {
        return _recipe;
    }

    void checkQuantity(int quantity, Partner partner) {

    }

    public String toString() {
        return super.toString() + "|" + _recipe.toString();
    }
}
