package ggc.core;

public class DefaultDeliveryMode implements NotificationDeliveryMode {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202109091821L;

    /**
     * Add notification to pending notifications.
     * 
     */
    @Override
    public Notification deliverNotification(String type, Product product, double price) {
        return new Notification(type, product, price);
    }
    
}