package m19.app.users;

import javax.sound.midi.Receiver;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.User;
import m19.app.exception.NoSuchUserException;

/**
 * 4.2.2. Show specific user.
 */
public class DoShowUser extends Command<LibraryManager> {

  private Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoShowUser(LibraryManager receiver) {
    super(Label.SHOW_USER, receiver);
    _id = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.parse();
      User user = _receiver.getUser(_id.value());
      if (user == null){
        throw new NoSuchUserException(_id.value());
      }
      _display.addLine(user.getDescription());
      _display.display();

  }
}
