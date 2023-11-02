package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.dialogs.MessagePane;

import java.awt.event.ActionEvent;

public class NewPackageAction extends AbstractClassyAction{

    public NewPackageAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-package.png"));
        putValue(NAME, "New Package");
        putValue(SHORT_DESCRIPTION, "New Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  OVO ISPOD JE TEST, DODACE SE VEROVATNO U NULLPOINTEREXCEPTION
         */
        Message message = new Message(PossibleErr.PROJECT_MUST_BE_SELECTED_TO_CREATE_A_PACKAGE);
        MessageGenerator msggenerator = new MessageGenerator();
        msggenerator.generateMsg(message);
        new MessagePane(message);
    }
}
