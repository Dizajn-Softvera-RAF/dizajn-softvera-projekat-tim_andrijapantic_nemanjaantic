package app.controller.serializerActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.implementation.ProjectNode;
import app.model.message.PossibleErr;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveProjectAction extends AbstractClassyAction {
    public SaveProjectAction() {
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(NAME, "Save action");
        putValue(SHORT_DESCRIPTION, "Save Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ( MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
            ProjectNode projectNode = (ProjectNode) MainFrame.getInstance().getSelectedNode().getClassyNode();
            File projectFile = null;
            if (!projectNode.isChanged()) {
                System.out.println("Projekat nije promenjen");
                return;
            }
            if (projectNode.getPath()==null || projectNode.getPath().isEmpty()) {
                JFileChooser jfc = new JFileChooser();
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    projectNode.setPath(projectFile.getPath());
                } else {
                    return;
                }
            }
            AppCore.getInstance().getSerializer().saveProject((ProjectNode) MainFrame.getInstance().getSelectedNode().getClassyNode());

            projectNode.setChanged(false);
        }
        else
            AppCore.getInstance().showMessage(PossibleErr.PROJECT_MUST_BE_SELECTED_FOR_THIS_ACTION);


    }
}
