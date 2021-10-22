package ggc.core;

import java.util.ArrayList;

public class Recipe {
    private double _alpha;
    private AggregateProduct _product;
    private ArrayList<Component> _components;

    public Recipe(AggregateProduct product, ArrayList<Component> components, double alpha) {
        _alpha = alpha;
        _components = components;
        _product = product;
    }

    public String toString() {
        String ret = "";

        for(Component component: _components) {
            ret += component.toString() + "#";
        }

        return ret.substring(0, ret.length()-1);
    }
}
