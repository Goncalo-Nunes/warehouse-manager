package ggc.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.io.Serializable;


public class Partner implements ProductObserver, Serializable {
    private String _name;
    private String _address;
    private String _id;
    private PartnerState _status;
    private double _points;
    private NotificationDeliveryMode _deliveryMode;
    private double _baseValues;
    private double _totalSalesValue;
    private double _acquisitionsValue;
    private List<Acquisition> _acquisitions = new ArrayList<Acquisition>();
    private List<Sale> _sales = new ArrayList<Sale>();
    private Set<Batch> _batches = new TreeSet<Batch>(new BatchComparator());
    private List<Notification> _notifications = new ArrayList<Notification>();

    Partner(String id, String name, String address) {
        this(id, name, address, new DefaultDeliveryMode());
    }

    Partner(String id, String name, String address, NotificationDeliveryMode mode) {
        _id = id;
        _name = name;
        _address = address;
        _status = new NormalPartner(this);
        _points = 0;
        _deliveryMode = mode;
        _baseValues = 0;
        _totalSalesValue = 0;
        _acquisitionsValue = 0;
    }

    String getId() {
        return _id;
    }

    double getPoints() {
        return _points;
    }

    void setPoints(double points) {
        _points = points;
    }

    Collection<Batch> getBatches() {
        return _batches;
    }

    void addBatch(Batch batch) {
        _batches.add(batch);
    }

    void removeBatch(Batch batch) {
        _batches.remove(batch);
    }

    Collection<Acquisition> getAcquisitions() {
        return _acquisitions;
    }

    Collection<Sale> getSales() {
        return _sales;
    }

    List<Notification> getNotifications() {
        List<Notification> _notificationsCopy = _notifications;
        _notifications = new ArrayList<Notification>();
        return Collections.unmodifiableList(_notificationsCopy);
    }

    void addSale(Sale sale) {
        _sales.add(sale);
        _baseValues += sale.getBaseValue();
    }

    PartnerState getStatus() {
        return _status;
    }

    void setStatus(PartnerState status) {
        _status = status;
    } 

    void addAcquisition(Acquisition acquisition) {
        _acquisitions.add(acquisition);
        _acquisitionsValue += acquisition.getBaseValue();
    }

    double calculateAcquisitionsValue() {
        Double total = 0d;

        for (Acquisition acquisition : _acquisitions) {
            total += acquisition.getBaseValue();
        }

        return total;
    }

    void paySale(SaleByCredit sale) {
        _status.pay(sale);
        _totalSalesValue += sale.getTotalValue();
    }

    public void update(String type, Product product, double price) {
        _notifications.add(_deliveryMode.deliverNotification(type, product, price));
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }

        if (!(other instanceof Partner)) {
            return false;
        }

        Partner partner = (Partner)other;

        return partner.getId().equals(_id);
    }

    public String toString() {
        return _id + "|" + _name + "|" + _address + "|" +_status + "|" + Math.round(_points) + "|" 
        + Math.round(_acquisitionsValue) + "|" + Math.round(_baseValues) + "|" + Math.round(_totalSalesValue);
    }
}
