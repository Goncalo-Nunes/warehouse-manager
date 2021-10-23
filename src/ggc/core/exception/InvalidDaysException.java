package ggc.core.exception;

public class InvalidDaysException extends Exception {

  /** Wrong days input. */
  private int _days;

  /** @param days invalid days to report. */
  public InvalidDaysException(int days) {
    _days = days;
  }

  /**
   * @return the invalid advance date days.
   */
  public int getInvalidDays() {
      return _days;
  }
}
