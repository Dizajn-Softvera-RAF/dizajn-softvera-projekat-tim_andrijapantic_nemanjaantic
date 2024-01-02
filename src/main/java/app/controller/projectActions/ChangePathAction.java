package app.controller.projectActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.implementation.ProjectNode;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ChangePathAction extends AbstractClassyAction {

    public ChangePathAction() {
        putValue(SMALL_ICON, loadIcon("/images/path.png"));
        putValue(NAME, "Change Path");
        putValue(SHORT_DESCRIPTION, "Change Path Of Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
                JFileChooser chooser = new JFileChooser();

                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int odabir = chooser.showOpenDialog(null);

                if (odabir == JFileChooser.APPROVE_OPTION) {
                    File selektovaniFolder = chooser.getSelectedFile();
                    String putanja = selektovaniFolder.getAbsolutePath();
                    Notification notification = new Notification(NotificationType.PATH_CHANGED, MainFrame.getInstance().getSelectedNode().getClassyNode().getId() , putanja);
                    MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(notification);
                }
            } else {
                AppCore.getInstance().showMessage(PossibleErr.NEED_TO_SELECT_PROJECT_TO_ADD_PATH);
                
            }
        } catch (NullPointerException exception) {
            AppCore.getInstance().showMessage(PossibleErr.NEED_TO_SELECT_PROJECT_TO_ADD_PATH);
            
        }


    }
}
