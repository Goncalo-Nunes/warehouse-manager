package ggc.core;

import java.io.Serializable;

public interface ObservableProduct extends Serializable {
    /**
     * Registers an Observer (Partner) to a Product.
     * 
     * @param observer
     *          Partner being added as an Observer.
     * @param p
     *          product being added to be observed by Observer.
     */
    void registerObserver(ProductObserver observer);

    /**
     * Removes an Observer (Partner) from a Product.
     * Partner no longer wants to receive notifications about the Observable (Product).
     * 
     * @param observer
     *          Partner being removed as an Observer.
     */
    void removeObserver(ProductObserver observer);

    /**
     * Notify all observers when the Observable's state has changed.
     * 
     * @param type
     *          Observable's new state (type).
     *          can be either NEW or BARGAIN.
     */
    void notifyObservers(String type);
}

