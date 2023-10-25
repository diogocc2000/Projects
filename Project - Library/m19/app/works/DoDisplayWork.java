package m19.app.works;

import m19.core.LibraryManager;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;

//imports adicionados

import pt.tecnico.po.ui.Input;
import m19.core.Work;
import m19.app.exception.NoSuchWorkException;


/**
 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {

  private Input<Integer> _id;
  /**
   * @param receiver
   */
  public DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    _id = _form.addIntegerInput(Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.parse();
      Work work = _receiver.getWork(_id.value());
      if (work == null){
        throw new NoSuchWorkException(_id.value());
      }
      _display.addLine(work.getDescription());
      _display.display();
    }
  
}
