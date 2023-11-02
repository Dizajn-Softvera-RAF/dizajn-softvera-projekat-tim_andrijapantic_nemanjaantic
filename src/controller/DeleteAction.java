package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.dialogs.MessagePane;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractClassyAction{
    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  OVO ISPOD JE TEST
         */
        Message message = new Message(PossibleErr.CANNOT_DELETE_ELEMENT);
        MessageGenerator msggenerator = new MessageGenerator();
        msggenerator.generateMsg(message);
    }
}
