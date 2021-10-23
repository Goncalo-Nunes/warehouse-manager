package ggc.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** Exception thrown when a partner key is duplicated. */
public class DuplicateProductKeyException extends CommandException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109091821L;

  /** @param key the duplicated key */
  public DuplicateProductKeyException(String key) {
    super(Message.duplicateProductKey(key));
  }

}
