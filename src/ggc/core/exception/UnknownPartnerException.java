package ggc.core.exception;


/** Exception thrown when a partner key is unkwnon */
public class UnknownPartnerException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201409301048L;
  
    /** Partner key. */
    private String _key;
  
    /** @param key the unknown key */
    public UnknownPartnerException(String key) {
      _key = key;
    }
  
    /**
     * @return the invalid Partner key.
     */
    public String getPartnerKey() {
      return _key;
    }
  
  }