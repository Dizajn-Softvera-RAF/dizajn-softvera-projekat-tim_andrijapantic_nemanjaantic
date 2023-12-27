package app.controller.serializerActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.implementation.DiagramNode;
import app.model.implementation.ProjectNode;
import app.model.message.PossibleErr;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveTemplateAction extends AbstractClassyAction {

    public SaveTemplateAction() {
        putValue(SMALL_ICON, loadIcon("/images/save-template.png"));
        putValue(NAME, "Save Diagram As Template");
        putValue(SHORT_DESCRIPTION, "Save Diagram As Template");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof DiagramNode) {

            String templateName = JOptionPane.showInputDialog("Enter Template Name: ");

            File templatesDir = new File("src/main/resources/templates/");
            File templateFile = new File(templatesDir, templateName.concat(".json"));

            String filepath = templateFile.getAbsolutePath();

            AppCore.getInstance().getSerializer().saveDiagram((DiagramNode) MainFrame.getInstance().getSelectedNode().getClassyNode(), filepath);
        }
        AppCore.getInstance().showMessage(PossibleErr.DIAGRAM_MUST_BE_SELECTED_FOR_THIS_ACTION);
    }
}
