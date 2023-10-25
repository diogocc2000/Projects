package m19.app.main;

import m19.core.LibraryManager;

import pt.tecnico.po.ui.Command;

//imports adicionados

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;




/**
 * 4.1.3. Advance the current date.
 */
public class DoAdvanceDate extends Command<LibraryManager> {


  private Input <Integer> _number;

  /**
   * @param receiver
   */
  public DoAdvanceDate(LibraryManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _number = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    _receiver.advanceDays(_number.value());
  
  }
  
}
