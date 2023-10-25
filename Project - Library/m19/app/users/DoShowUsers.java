package m19.app.users;

import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import java.util.LinkedHashMap; 
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map; 
import m19.core.User;

/**
 * 4.2.4. Show all users.
 */
public class DoShowUsers extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoShowUsers(LibraryManager receiver) {
    super(Label.SHOW_USERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    Map <Integer,User> users = _receiver.getUsers();
    // Ordenar os Users 
    List<User> userByName = new ArrayList<>(users.values());
    Collections.sort(userByName);

    for (User user : userByName) {
      _display.addLine(user.getDescription());
    }
    _display.display(); 
  }
  
}
