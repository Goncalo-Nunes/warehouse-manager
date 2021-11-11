package ggc.core.exception;

public class UnavailableProductQuantityException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201409301048L;
  
    /** Amount */
    private int _amount;

    private String _productId;
    private int _available;
  
    
    public UnavailableProductQuantityException(String productId, int available, int amount) {
      _productId = productId;
      _available = available;
      _amount = amount;
    }
  
    /**
     * @return the unavailable amount
     */
    public int getAmount() {
      return _amount;
    }

    public int getAvailable() {
      return _available;
    }

    public String getProductId() {
      return _productId;
    }
  
  }
