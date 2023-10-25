package m19.app.works;

import m19.core.LibraryManager;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import java.util.LinkedHashMap; 
import java.util.Map; 
import m19.core.Work;


/**
 * 4.3.2. Display all works.
 */
public class DoDisplayWorks extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoDisplayWorks(LibraryManager receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    Map<Integer,Work> works = _receiver.getWorks(); 
    for (Map.Entry<Integer,Work> entry : works.entrySet()){
      Work work = entry.getValue();
      _display.addLine(work.getDescription());
    }
    _display.display();
  }
}
