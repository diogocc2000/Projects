package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.app.exception.*;
import m19.core.User;
import m19.core.Request;

/**
 * 4.2.5. Settle a fine.
 */
public class DoPayFine extends Command<LibraryManager> {

  private Input<Integer> _userId;

  /**
   * @param receiver
   */
  public DoPayFine(LibraryManager receiver) {
    super(Label.PAY_FINE, receiver);
    _userId = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    int exception = _receiver.payFine(_userId.value());
    if(exception == -1)
      throw new NoSuchUserException(_userId.value());
    if(exception == -2)
      throw new UserIsActiveException(_userId.value());
  }

}
