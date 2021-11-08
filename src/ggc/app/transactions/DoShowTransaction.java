package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownTransactionKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.UnknownTransactionException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    addIntegerField("id", Message.requestTransactionKey());

  }

  @Override
  public final void execute() throws CommandException {
    int id = integerField("id");
    try {
      _display.popup(_receiver.getTransactionWithId(id));
    } catch (UnknownTransactionException e) {
      throw new UnknownTransactionKeyException(e.getTransactionKey());
    }
    
  }

}
