package ggc.core.exception;

public class UnknownTransactionException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201409301048L;

    /** Transaction key. */
    private int _key;
    
    /** @param key the unknown key */
    public UnknownTransactionException(int key) {
        _key = key;
    }
    
    /**
     * @return the invalid Transaction key.
     */
    public int getTransactionKey() {
        return _key;
    }
}
