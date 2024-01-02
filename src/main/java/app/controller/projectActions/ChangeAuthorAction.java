package app.controller.projectActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.view.mainframe.MainFrame;
import app.model.implementation.ProjectNode;
import app.view.tree.ClassyTreeView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractClassyAction {
    public ChangeAuthorAction() {
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Change Author");
        putValue(SHORT_DESCRIPTION, "Change Author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
                String input = JOptionPane.showInputDialog("Unesi ime autora ");
                ClassyTreeView projectExplorer = MainFrame.getInstance().getClassyTree().getTreeView();
                Notification notification = new Notification(NotificationType.CHANGE_AUTHOR, input);
                projectExplorer.notifySubscribers(notification);
            } else {
                AppCore.getInstance().showMessage(PossibleErr.NEED_TO_SELECT_PROJECT_TO_ADD_AUTHOR);
                
            }
        } catch (NullPointerException exception) {
            AppCore.getInstance().showMessage(PossibleErr.NEED_TO_SELECT_PROJECT_TO_ADD_AUTHOR);
            
        }
    }
}
