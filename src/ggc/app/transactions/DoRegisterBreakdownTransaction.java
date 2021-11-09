package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.core.Transaction;
import ggc.app.exception.UnavailableProductException;
import ggc.core.exception.UnknownProductException;

//FIXME import classes

/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    
    addStringField("product", Message.requestProductKey());
    addIntegerField("quantity", Message.requestAmount());
    addStringField("id", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    String product = stringField("name");
    Integer quantity = integerField("quantity");
    String id = stringField("id");
    try {
      _receiver.registerBreakdownTransaction(product, quantity, id);
    } catch (UnknownProductException e) {
      throw new UnavailableProductException(e.getProductKey());
    }

}
}