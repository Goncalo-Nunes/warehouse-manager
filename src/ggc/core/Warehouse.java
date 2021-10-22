package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import ggc.core.exception.BadEntryException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    // FIXME define attributes
    // FIXME define contructor(s)
    // FIXME define methods

    private Date _date;
    private int _nextTransactionId;
    private HashMap<String, Product> _products;
    private HashMap<String, Partner> _partners;
    private HashMap<Integer, Transaction> _transactions;



    public HashMap<String, Product> getProducts() {
        return _products;
    }

    public HashMap<String, Partner> getPartners() {
        return _partners;
    }

    public HashMap<Integer, Transaction> getTransactions() {
        return _transactions;
    }

    /**
     * @param txtfile filename to be loaded.
     * @throws IOException
     * @throws BadEntryException
     */
    void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
        //FIXME implement method
        Parser parser = new Parser(this);
        parser.parseFile(txtfile);
    }

    public void registerPartner(String id, String name, String address) { // throws
        _partners.put(id, new Partner(id, name, address));
    }

    public void registerSimpleProduct(String productId) {
        _products.put(productId, new SimpleProduct(productId));
    }

    public void registerAggregateProduct(String productId, ArrayList<Product> products, ArrayList<Integer> quantities, double alpha) {
        ArrayList<Component> components = new ArrayList<>();
        AggregateProduct aggregateProduct = new AggregateProduct(productId);

        for(int i = 0; i < products.size(); i++) {
            components.add(new Component(quantities.get(i), products.get(i)));
        }

        aggregateProduct.setRecipe(new Recipe(aggregateProduct, components, alpha));
        _products.put(productId, aggregateProduct);
    }

    public Product getProductWithId(String id) {
        return _products.get(id);
    }

    public Partner getPartnerWithId(String id) {
        return _partners.get(id);
    }

}
