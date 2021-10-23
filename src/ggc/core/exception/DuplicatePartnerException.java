package ggc.core.exception;


/** Exception thrown when a partner key is duplicated. */
public class DuplicatePartnerException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201409301048L;
  
    /** Partner key. */
    private String _key;
  
    /** @param key the duplicated key */
    public DuplicatePartnerException(String key) {
      _key = key;
    }
  
    /**
     * @return the invalid Partner key.
     */
    public String getPartnerKey() {
      return _key;
    }
  
  }
