package ggc.core;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.DuplicatePartnerException;
import ggc.core.exception.InvalidDaysException;
import ggc.core.exception.UnknownPartnerException;
import ggc.core.exception.UnknownProductException;
import ggc.core.exception.UnknownTransactionException;


/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;


    private Date _date;
    private int _nextTransactionId;
    private int _availableBalance;
    private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);
    private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

    Warehouse() {
        _date = new Date();
        _nextTransactionId = 0;
        _availableBalance = 0;
    }

    Date getDate() {
        return _date;
    }

    void advanceDate(int offset) throws InvalidDaysException {
        if (offset <= 0) {
            throw new InvalidDaysException(offset);
        }

        _date.add(offset);

        for(Transaction transaction : _transactions.values()) {
            transaction.setCurrentDate(_date);
        }
    }

    Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(_products.values());
    }

    Collection<Partner> getPartners() {
        return Collections.unmodifiableCollection(_partners.values());
    }

    Collection<Transaction> getTransactions() {
        return Collections.unmodifiableCollection(_transactions.values());
    }

    List<Batch> getAllBatchesSorted() {
        ArrayList<Batch> batches = new ArrayList<Batch>();

        for(Product product : getProducts()) {
          for(Batch batch : product.getBatches()) {
            batches.add(batch);
          }
        }
    
        batches.sort(new BatchComparator());
    
        return Collections.unmodifiableList(batches);
    }


    /**
     * @param txtfile filename to be loaded.
     * @throws IOException
     * @throws BadEntryException
     * @throws UnknownPartnerException
     * @throws UnknownProductException
     * @throws NumberFormatException
     */
    void importFile(String txtfile) throws IOException, BadEntryException, DuplicatePartnerException, UnknownPartnerException, NumberFormatException, UnknownProductException {
        Parser parser = new Parser(this);
        parser.parseFile(txtfile);
    }

    void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
        if(_partners.containsKey(id)) {
            throw new DuplicatePartnerException(id);
        }
        Partner partner =  new Partner(id, name, address);

        for(Product product : _products.values()) {
            product.registerObserver(partner);
        }
        _partners.put(id, partner);
    }

    void makeProductOberversInterested(Product product) {
        for (ProductObserver observer : _partners.values()) {
            product.registerObserver(observer);
        }
    }

    void registerSimpleProduct(String productId) {
        SimpleProduct product = new SimpleProduct(productId);
        makeProductOberversInterested(product);
        _products.put(productId, product);
    }

    void registerAggregateProduct(String productId, List<Product> products, List<Integer> quantities, double alpha) {

        List<Component> components = new ArrayList<Component>();
        AggregateProduct aggregateProduct = new AggregateProduct(productId);

        for(int i = 0; i < products.size(); i++) {
            components.add(new Component(quantities.get(i), products.get(i)));
        }

        aggregateProduct.setRecipe(new Recipe(aggregateProduct, components, alpha));
        makeProductOberversInterested(aggregateProduct);
        _products.put(productId, aggregateProduct);
    }

    Product getProductWithId(String id) throws UnknownProductException {
        if(!productExists(id)) {
            throw new UnknownProductException(id);
        }
        return _products.get(id);
    }

    Collection<Batch> getBatchesFromProduct(String id) throws UnknownProductException {
        return getProductWithId(id).getBatches();
    }

    Collection<Batch> getBatchesFromPartner(String id) throws UnknownPartnerException {
        return getPartnerWithId(id).getBatches();
    }

    List<Batch> getBatchesUnderGivenPrice(int price) {
        List<Batch> lookup = new ArrayList<>();
        for(Product product : getProducts()) {
          for(Batch batch : product.getBatches()) {
            if (price < batch.getPrice())
                lookup.add(batch);
          }
        }
        lookup.sort(new BatchComparator());
        return Collections.unmodifiableList(lookup);

    }

    Partner getPartnerWithId(String id) throws UnknownPartnerException {
        if(!partnerExists(id)) {
            throw new UnknownPartnerException(id);
        }

        return _partners.get(id);
    }

    boolean partnerExists(String id) {
        return _partners.containsKey(id);
    }

    boolean productExists(String id) {
        return _products.containsKey(id);
    }

    Collection<Acquisition> getAcquisitionsFromPartner(String id) throws UnknownPartnerException {
        return getPartnerWithId(id).getAcquisitions();
    }

    Collection<Sale> getSalesFromPartner(String id) throws UnknownPartnerException {
        return getPartnerWithId(id).getSales();
    }

    Transaction getTransactionWithId(int id) throws UnknownTransactionException {
        if(!_transactions.containsKey(id)) {
            throw new UnknownTransactionException(id);
        }
        
        return _transactions.get(id);
    }

    void toggleNotifications(Product product, ProductObserver observer) {
        if(product.observerExists(observer)) {
            product.removeObserver(observer);
        } else {
            product.registerObserver(observer);
        }
      }


    List<Transaction> getPaymentsPartner(String id) throws UnknownPartnerException {
        ArrayList<Transaction> payments = new ArrayList<>();
        Partner partner = getPartnerWithId(id);
        
        for(Transaction transaction : getTransactions()) {
            if(transaction.isPaid() && transaction.getPartner().equals(partner))
                payments.add(transaction);
        }
        
        return Collections.unmodifiableList(payments);
    }

    void registerBreakdownTransaction(Product product, int quantity, Partner partner) throws UnknownPartnerException {
        //FIXME check quantidade com stock, check if derivado
        
    }

    void registerAcquisitionTransaction(Partner partner, Product product, double price, int quantity) {
        Acquisition acquisition = new Acquisition(product, quantity, partner, price);
    
        _transactions.put(_nextTransactionId, acquisition);

        partner.addAcquisition(acquisition);
        product.addBatch(price, quantity, partner);

        _nextTransactionId++;
        _availableBalance -= price;
    }

    void pay(Transaction transaction) {
        if (transaction.isPaid()) {
            return;
        }

        transaction.pay();
    }


}
