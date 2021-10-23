package ggc.core.exception;

/** Exception thrown when a product key is duplicated. */
public class DuplicateProductException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201409301048L;
  
    /** Product key. */
    private String _key;
  
    /** @param key the duplicated key */
    public DuplicateProductException(String key) {
      _key = key;
    }
  
    /**
     * @return the invalid product key.
     */
    public String getProductKey() {
      return _key;
    }
  
  }