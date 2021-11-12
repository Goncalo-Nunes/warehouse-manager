package ggc.core;

import java.io.Serializable;

public interface NotificationDeliveryMode extends Serializable {
    /**
     * Add notification to pending notifications.
     */
    Notification deliverNotification(String type, Product product, double price); 
}
