package ggc.core;

public interface ProductObserver {
    /**
     * Adds a notification to array of Client notifications.
     * 
     * @param type
     *          notification type.
     * @param productID
     *          product ID.
     * @param price
     *          product price.
     */
    void update(String type, Product product);
}
