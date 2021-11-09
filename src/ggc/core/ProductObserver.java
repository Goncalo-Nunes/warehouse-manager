package ggc.core;

public interface ProductObserver {
    /**
     * Adds a notification to array of Client notifications.
     * 
     * @param type
     *          notification type.
     * @param product
     *          product
     */
    void update(String type, Product product);

    int hashCode();

    boolean equals(Object other);
}
