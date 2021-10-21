package ggc.core;

import java.util.HashSet;

public class Partner {
    private String _name;
    private String _address;
    private String _id;
    private String _status;
    private double _points;
    private HashSet<Acquisition> _acquisitions;
    private HashSet<Sale> _sales;
    private HashSet<Batch> _batches;
    private ArrayList<Notification> _notifications;
}
