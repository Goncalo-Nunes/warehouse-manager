package ggc.core;

public class DefaultDeliveryMode implements NotificationDeliveryMode {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202012040059L;

    /**
     * Add notification to pending notifications.
     * 
     */
    @Override
    public Notification deliverNotification(String type, Product product) {
        return new Notification(type, product);
    }
    
}