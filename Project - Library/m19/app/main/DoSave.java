package m19.app.main;

import m19.core.LibraryManager;
import m19.core.exception.MissingFileAssociationException;
import pt.tecnico.po.ui.Command;

//imports adicionados

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;


/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<LibraryManager> {
  
  private Input<String> _filename;

  /**
   * @param receiver
   */
  public DoSave(LibraryManager receiver) {
    super(Label.SAVE, receiver);
    _filename = _form.addStringInput(Message.newSaveAs());
  }


  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    if(_receiver.getFilename() == null){
      _form.parse();
      _receiver.setFilename(_filename.value());
  }
  try {
      _receiver.save();
    } catch (IOException ioe) {
      _display.addLine("IOException.");
    }
      catch (MissingFileAssociationException mfae) {
        _display.addLine("MissingFileAssociationException.");
      }
    _display.display();
    }
}
