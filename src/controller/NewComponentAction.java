package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.dialogs.MessagePane;

import java.awt.event.ActionEvent;

public class NewComponentAction extends AbstractClassyAction{

    public NewComponentAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-component.png"));
        putValue(NAME, "New Component");
        putValue(SHORT_DESCRIPTION, "New Component");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  OVO ISPOD JE TEST, DODACE SE VEROVATNO U NULLPOINTEREXCEPTION
         */
        Message message = new Message(PossibleErr.PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_COMPONENT);
        MessageGenerator msggenerator = new MessageGenerator();
        msggenerator.generateMsg(message);
    }
}
