package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.dialogs.InfoDialog;
import view.dialogs.MessagePane;
import view.mainframe.MainFrame;

import java.awt.event.ActionEvent;

public class InfoAction extends AbstractClassyAction {

    public InfoAction() {

        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new InfoDialog(MainFrame.getInstance(), "Info Window");
    }
}
