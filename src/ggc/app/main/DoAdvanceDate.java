package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import ggc.app.exception.InvalidDateException;
import ggc.core.exception.InvalidDaysException;

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("number", Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws CommandException {
    Integer number = integerField("number");
    
    try {
      _receiver.advanceDate(number);
    } catch (InvalidDaysException e) {
      throw new InvalidDateException(e.getInvalidDays());
    }
    
  }

}
