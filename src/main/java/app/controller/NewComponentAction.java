package app.controller;

import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;

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
         *  OVO ISPOD JE TEST, DODACE SE VEROVATNO U NULLPOINTEREXCEPTION KADA SE DODAJE KOMPONENTA
         */
        Message message = new Message(PossibleErr.DIAGRAM_MUST_BE_SELECTED_TO_CREATE_A_COMPONENT);
        MessageGenerator msggenerator = new MessageGenerator();
        msggenerator.generateMsg(message);
    }
}
