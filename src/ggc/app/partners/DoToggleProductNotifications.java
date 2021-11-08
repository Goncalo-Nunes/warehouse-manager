package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownProductKeyException;
//FIXME import classes

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    //FIXME add command fields
    addStringField("id", Message.requestProductKey());
    addStringField("key", Message.requestPartnerKey());

  }

  @Override
  public void execute() throws CommandException {
    String key = stringField("key");
    String id = stringField("id");
    try {
      for(Partner parner : getNotifications()) {
        if (_receiver.getPartnerNotifications(id) = false) {

      }
    } 
      catch (UnknownProductKeyException e) {
      throw new UnknownProductKeyException(e.getProductKey());
  }

}
