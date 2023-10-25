package m19.app.works;

import m19.core.LibraryManager;

//imports adicionados

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import java.util.ArrayList;
import java.util.List;
import m19.core.Work;


/**
 * 4.3.3. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {

  private Input <String> _term;

  /**
   * @param m
   */
  public DoPerformSearch(LibraryManager m) {
    super(Label.PERFORM_SEARCH, m);
    _term = _form.addStringInput(Message.requestSearchTerm());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    List<Work> works = _receiver.searchWorks(_term.value());
    for (Work w : works) { 
      _display.addLine(w.getDescription());
    }
    _display.display(); 
  }
}
