package m19.app.requests;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Form;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.User;
import m19.core.Work;
import m19.app.exception.*;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {

  private Input<Integer> _userId;
  private Input<Integer> _workId;

  /**
   * @param receiver
   */
  public DoReturnWork(LibraryManager receiver) {
    super(Label.RETURN_WORK, receiver);
    _userId = _form.addIntegerInput(Message.requestUserId());
    _workId = _form.addIntegerInput(Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    int id = _receiver.returnWork(_userId.value(), _workId.value());
    if(id == -1)
      throw new NoSuchUserException(_userId.value());
    if(id == -2)
      throw new NoSuchWorkException(_workId.value());
    if(id == -3)
      throw new WorkNotBorrowedByUserException(_workId.value(), _userId.value());
    else {
      User user = _receiver.getUser(_userId.value());
      if (user.getFine() > 0){
        Form formAux = new Form();
        Input<Boolean> aws = formAux.addBooleanInput(Message.requestFinePaymentChoice());
        _display.addLine(Message.showFine(_userId.value(), user.getFine() ));
        _display.display();
        formAux.parse();
        if(aws.value()){
          user.payFine();
        }
      }
    }
    
  }

}
