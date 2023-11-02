package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.dialogs.MessagePane;

import java.awt.event.ActionEvent;

public class InfoAction extends AbstractClassyAction {

    public InfoAction() {

        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  OVO ISPOD JE TEST, I OVAJ MESSAGE SE NECE KORISTITI OVDE
         */
        Message message = new Message(PossibleErr.ONLY_USE_DOUBLE_CLICK_ON_NODES);
        MessageGenerator msggenerator = new MessageGenerator();
        msggenerator.generateMsg(message);
        new MessagePane(message);
    }
}
