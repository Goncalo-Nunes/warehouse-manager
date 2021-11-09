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
    private String _status;
    private double _points;
    private NotificationDeliveryMode _deliveryMode;
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
        _status = "NORMAL";
        _points = 0;
        _deliveryMode = mode;
    }

    String getId() {
        return _id;
    }

    Collection<Batch> getBatches() {
        return _batches;
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

    double calculateAcquisitionsValue() {
        Double total = 0d;

        for (Acquisition acquisition : _acquisitions) {
            total += acquisition.getBaseValue();
        }

        return total;
    }

    double calculateSalesValue() {
        Double total = 0d;

        for (Sale sale : _sales) {
            total += sale.getAmountPaid();
        }
        return total;
    }

    double calculateSalesPayedValue() {
        Double total = 0d;

        for (Sale sale : _sales) {
            if(sale.isPaid()) {
                total += sale.getAmountPaid();
            }
        }

        return total;
    }

    public void update(String type, Product product) {
        _notifications.add(_deliveryMode.deliverNotification(type, product));
    }

    public int hashCode() {
        return _id.hashCode();
    }

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
        + Math.round(calculateAcquisitionsValue()) + "|" + Math.round(calculateSalesValue()) + "|" + Math.round(calculateSalesPayedValue());
    }
}
