package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownProductKeyException;
//FIXME import classes
import ggc.core.exception.UnknownPartnerException;
import ggc.core.exception.UnknownProductException;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());

  }

  @Override
  public void execute() throws CommandException {
    String partnerId = stringField("partnerId");
    String productId = stringField("productId");

    try {
      _receiver.togglePartnerNotifications(productId, partnerId);
    } catch (UnknownPartnerException e) {
      throw new UnknownPartnerKeyException(e.getPartnerKey());
    } catch (UnknownProductException e) {
      throw new UnknownProductKeyException(e.getProductKey());
    }
  }
}
