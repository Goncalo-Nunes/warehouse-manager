package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

import ggc.core.WarehouseManager;
import ggc.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
  }

  @Override
  public final void execute() throws CommandException {

    if(!_receiver.isFilenameSet()) {
      String filename = Form.requestString(Message.newSaveAs());
      try {
        _receiver.saveAs(filename);
      } catch (IOException | MissingFileAssociationException e) {
        e.printStackTrace();
      }

    } else {
      try {
        _receiver.save();
      } catch (IOException | MissingFileAssociationException e) {
        try {
          String filename = Form.requestString(Message.saveAs());
          _receiver.saveAs(filename);
        } catch (IOException | MissingFileAssociationException e2) {
          e2.printStackTrace();
        }

      }
      
    }

  }

}
