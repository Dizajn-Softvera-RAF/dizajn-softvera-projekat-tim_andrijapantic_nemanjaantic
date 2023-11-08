package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.mainframe.MainFrame;
import view.repository.implementation.ProjectNode;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractClassyAction{
    public ChangeAuthorAction() {
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
                String input = JOptionPane.showInputDialog("Unesi ime autora ");
            }
            else {
                Message message = new Message(PossibleErr.NEED_TO_SELECT_PROJECT_TO_ADD_AUTHOR);
                MessageGenerator msggenerator = new MessageGenerator();
                msggenerator.generateMsg(message);
            }
        } catch (NullPointerException exception) {
            Message message = new Message(PossibleErr.NEED_TO_SELECT_PROJECT_TO_ADD_AUTHOR);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);
        }
    }
}
