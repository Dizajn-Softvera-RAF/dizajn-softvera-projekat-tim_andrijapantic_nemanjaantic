package app.controller;

import app.model.implementation.PackageNode;
import app.model.implementation.ProjectNode;
import app.view.mainframe.MainFrame;

import java.awt.event.ActionEvent;
import java.io.File;

public class CodeGenerationAction extends AbstractClassyAction{

    public CodeGenerationAction() {
        putValue(SMALL_ICON, loadIcon("/images/generate-code.png"));
        putValue(NAME, "Generate Code for Project");
        putValue(SHORT_DESCRIPTION, "Generate Code for Project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String path = System.getProperty("user.home") + File.separator +"Desktop";
        if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ProjectNode) {
            ProjectNode projectNode = (ProjectNode)MainFrame.getInstance().getSelectedNode().getClassyNode();
            File projectFolder = new File(path, projectNode.getName());
            projectFolder.mkdirs();
            System.out.println("Napravljen projekat" + projectNode.getName() + "na putanji: " + projectFolder.getAbsolutePath());
            for (PackageNode packageNode: projectNode.getChildren()) {
                packageNode.generateCodeStructure(projectFolder.getAbsolutePath());
            }
        }
    }
}
