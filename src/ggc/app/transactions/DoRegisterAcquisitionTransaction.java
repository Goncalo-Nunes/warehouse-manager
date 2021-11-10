package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.ArrayList;
import java.util.List;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.core.exception.UnknownPartnerException;
import ggc.core.exception.UnknownProductException;

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
    addStringField("price", Message.requestPrice());
    addStringField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    String productId = stringField("productId");
    String partnerId = stringField("partnerId");
    double price = realField("price");
    int quantity = integerField("quantity");

    if(!_receiver.productExists(productId)) {
      Form form = new Form();
      form.addBooleanField("addRecipe", Message.requestAddRecipe());
      form.parse();
  
      if(booleanField("addRecipe")) {
        int numberComponents = Form.requestInteger(Message.requestNumberOfComponents());
        double alpha = Form.requestInteger(Message.requestAlpha());
        List<String> productIds = new ArrayList<String>(); 
        List<Integer> quantities = new ArrayList<Integer>();
  
        for(int i = 0; i < numberComponents; i++) {
          productIds.add(Form.requestString(Message.requestProductKey()));
          quantities.add(Form.requestInteger(Message.requestAmount()));
        }
  
        try {
          _receiver.registerAggregateProduct(productId, productIds, quantities, alpha);
        } catch (UnknownProductException e) {
          throw new UnknownProductKeyException(e.getProductKey());
        }
        
      } else {
        _receiver.registerSimpleProduct(productId);
      }
    } 

    try {
      _receiver.registerAcquisitionTransaction(partnerId, productId, price, quantity);
    } catch (UnknownPartnerException e) {
      throw new UnknownPartnerKeyException(e.getPartnerKey());
    } catch (UnknownProductException e) {
      e.printStackTrace();
    }


  }

}
