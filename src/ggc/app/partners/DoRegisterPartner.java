package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.exception.DuplicatePartnerException;
import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.core.WarehouseManager;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("id", Message.requestPartnerKey());
    addStringField("name", Message.requestPartnerName());
    addStringField("address", Message.requestPartnerAddress());
  }

  @Override
  public void execute() throws CommandException {
    String id = stringField("id");
    String name = stringField("name");
    String address = stringField("address");

    try {
      _receiver.registerPartner(id, name, address);
    } catch (DuplicatePartnerException e) {
      throw new DuplicatePartnerKeyException(e.getPartnerKey());
    }

  }

}
