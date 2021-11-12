package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnavailableProductException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.exception.UnavailableProductQuantityException;
import ggc.core.exception.UnknownPartnerException;
import ggc.core.exception.UnknownProductException;



/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
    addIntegerField("amount", Message.requestAmount());

  }

  @Override
  public final void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    String productId = stringField("productId");
    Integer amount = integerField("amount");


    try {
      _receiver.registerBreakdownTransaction(partnerId, productId, amount);
    } catch (UnknownPartnerException e) {
      throw new UnknownPartnerKeyException(e.getPartnerKey());
    } catch (UnknownProductException e) {
      throw new UnknownProductKeyException(e.getProductKey());
    } catch (UnavailableProductQuantityException e) {
      throw new UnavailableProductException(e.getProductId(), e.getAvailable(), e.getAmount());
  }
  }
}