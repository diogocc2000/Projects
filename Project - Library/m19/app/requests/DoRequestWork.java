package m19.app.requests;

import javax.sound.midi.Receiver;

import m19.app.exception.RuleFailedException;
import m19.core.LibraryManager;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Form;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.core.User;
import m19.core.Work;
import m19.core.Request;
import m19.app.exception.*;
/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

  private Input<Integer> _userId;
  private Input<Integer> _workId;


  /**
   * @param receiver
   */
  public DoRequestWork(LibraryManager receiver) {
    super(Label.REQUEST_WORK, receiver);
    _userId = _form.addIntegerInput(Message.requestUserId());
    _workId = _form.addIntegerInput(Message.requestWorkId());
  }



  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    int ruleIndex = _receiver.requestWork(_userId.value(),_workId.value());
    if(ruleIndex == -1)
      throw new NoSuchUserException(_userId.value());
    if(ruleIndex == -2)
      throw new NoSuchWorkException(_workId.value());
    if (ruleIndex == 0){
      Request request = _receiver.searchRequest(_userId.value(),_workId.value());
      int currentDate = _receiver.getCurrentDate();
      _display.addLine(Message.workReturnDay(_workId.value(), currentDate + request.getDeadline()));
      _display.display();
      return;
    }
    else if (ruleIndex == 3){
      Form formAux = new Form();
      Input<Boolean> aws = formAux.addBooleanInput(Message.requestReturnNotificationPreference());
      formAux.parse();
      if(aws.value()){
        User user = _receiver.getUser(_userId.value());
        Work work = _receiver.getWork(_workId.value());
        _receiver.warnUserWhenWorkIsAvailable(user,work);
      }
    }
    else{
      throw new RuleFailedException(_userId.value(), _workId.value(), ruleIndex);
    }
  }

}

