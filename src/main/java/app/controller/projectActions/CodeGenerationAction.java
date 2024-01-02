package app.controller.projectActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.implementation.PackageNode;
import app.model.implementation.ProjectNode;
import app.model.message.PossibleErr;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class CodeGenerationAction extends AbstractClassyAction {

    public CodeGenerationAction() {
        putValue(SMALL_ICON, loadIcon("/images/generate-code.png"));
        putValue(NAME, "Generate Code for Project");
        putValue(SHORT_DESCRIPTION, "Generate Code for Project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
                String path;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                } else {
                    return;
                }
                ProjectNode projectNode = (ProjectNode)MainFrame.getInstance().getSelectedNode().getClassyNode();
                File projectFolder = new File(path, projectNode.getName());
                projectFolder.mkdirs();
                System.out.println("Napravljen generisan kod za Projekat: " + projectNode.getName() + " na putanji: " + projectFolder.getAbsolutePath());
                for (PackageNode packageNode: projectNode.getChildren()) {
                    packageNode.generateCodeStructure(projectFolder.getAbsolutePath());
                }
                AppCore.getInstance().showMessage(PossibleErr.SUCCESSFULLY_GENERATED_CODE);
            } else
                AppCore.getInstance().showMessage(PossibleErr.PROJECT_MUST_BE_SELECTED_FOR_THIS_ACTION);
        } catch (NullPointerException exception) {
            AppCore.getInstance().showMessage(PossibleErr.PROJECT_MUST_BE_SELECTED_FOR_THIS_ACTION);
        }

    }
}
