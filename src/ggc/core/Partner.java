package ggc.core;

import java.util.ArrayList;
import java.util.HashSet;

public class Partner {
    private String _name;
    private String _address;
    private String _id;
    private String _status;
    private double _points;
    private double _salesValue;
    private double _salesPayed;
    private HashSet<Acquisition> _acquisitions;
    private HashSet<Sale> _sales;
    private HashSet<Batch> _batches;
    private ArrayList<Notification> _notifications;

    Partner(String id, String name, String address) {
        _id = id;
        _name = name;
        _address = address;
        _status = "NORMAL";
        _points = 0;
    }

    public String getId() {
        return _id;
    }

    public String toString() {
        return _id + "|" + _name + "|" + _status + "|" + _points + "|" + 
        _salesValue + "|" + _salesPayed;
    }
}
