package ggc.core.exception;

/** Exception thrown when a Product key is unkwnon */
public class UnknownProductException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201409301048L;
  
    /** Product key. */
    private String _key;
  
    /** @param key the unknown key */
    public UnknownProductException(String key) {
      _key = key;
    }
  
    /**
     * @return the invalid Product key.
     */
    public String getProductKey() {
      return _key;
    }
  
  }
