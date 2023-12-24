package app.controller.serializerActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.implementation.ProjectNode;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveProjectAsAction extends AbstractClassyAction {
    public SaveProjectAsAction() {
        putValue(NAME, "Save Project As");
        putValue(SHORT_DESCRIPTION, "Save As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ( MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
            ProjectNode projectNode = (ProjectNode) MainFrame.getInstance().getSelectedNode().getClassyNode();
            File projectFile = null;

            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                projectNode.setPath(projectFile.getPath());
            } else {
                return;
            }

            AppCore.getInstance().getSerializer().saveProject((ProjectNode) MainFrame.getInstance().getSelectedNode().getClassyNode());

        }



    }
}
