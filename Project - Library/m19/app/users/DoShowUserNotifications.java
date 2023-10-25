package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.User;
import m19.core.Notification;
import java.util.List;
import m19.app.exception.NoSuchUserException;


/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

  private Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(Label.SHOW_USER_NOTIFICATIONS, receiver);
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
    List<Notification> notifications = user.getNotifications();

    for (Notification n : notifications) {
      _display.addLine(n.getMessage());
    }
    user.removeNotifications();
    _display.display();
  }

}
