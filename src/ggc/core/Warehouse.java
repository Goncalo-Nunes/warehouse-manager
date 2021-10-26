package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.DuplicatePartnerException;
import ggc.core.exception.InvalidDaysException;
import ggc.core.exception.UnknownPartnerException;


/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;


    private Date _date;
    private int _nextTransactionId;
    private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);
    private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

    public Warehouse() {
        _date = new Date();
        _nextTransactionId = 0;
    }

    public Date getDate() {
        return _date;
    }

    public void advanceDate(int offset) throws InvalidDaysException {
        if (offset <= 0) {
            throw new InvalidDaysException(offset);
        }

        _date.add(offset);
    }

    public Map<String, Product> getProducts() {
        return Collections.unmodifiableMap(_products);
    }

    public Map<String, Partner> getPartners() {
        return Collections.unmodifiableMap(_partners);
    }

    public Map<Integer, Transaction> getTransactions() {
        return _transactions;
    }

    /**
     * @param txtfile filename to be loaded.
     * @throws IOException
     * @throws BadEntryException
     * @throws UnknownPartnerException
     */
    void importFile(String txtfile) throws IOException, BadEntryException, DuplicatePartnerException, UnknownPartnerException {
        Parser parser = new Parser(this);
        parser.parseFile(txtfile);
    }

    public void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
        if(_partners.containsKey(id)) {
            throw new DuplicatePartnerException(id);
        }

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

    public Partner getPartnerWithId(String id) throws UnknownPartnerException {
        if(!partnerExists(id)) {
            throw new UnknownPartnerException(id);
        }

        return _partners.get(id);
    }

    public boolean partnerExists(String id) {
        return _partners.containsKey(id);
    }

    public boolean productExists(String id) {
        return _products.containsKey(id);
    }

}
