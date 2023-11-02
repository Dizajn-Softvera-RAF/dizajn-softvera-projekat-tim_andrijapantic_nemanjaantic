package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.dialogs.MessagePane;

import java.awt.event.ActionEvent;

public class NewProjectAction extends AbstractClassyAction{

    public NewProjectAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-project.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  OVO ISPOD JE TEST, DODACE SE VEROVATNO U NULLPOINTEREXCEPTION
         */
        Message message = new Message(PossibleErr.PROJECT_EXPLORER_MUST_BE_SELECTED_TO_CREATE_A_PROJECT);
        MessageGenerator msggenerator = new MessageGenerator();
        msggenerator.generateMsg(message);
        new MessagePane(message);
    }
}
