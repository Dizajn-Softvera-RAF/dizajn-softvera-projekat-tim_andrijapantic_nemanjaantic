package app.view.mainframe;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu() {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu functions = new JMenu("Functions");


        JMenuItem miNew = new JMenuItem("New");
        JMenuItem miSaveAsImage = new JMenuItem("Save Diagram as Image");
        JMenuItem miSave = new JMenuItem("Save Project");
        JMenuItem miSaveAs = new JMenuItem("Save Project As");
        JMenuItem miOpen = new JMenuItem("Open Project");
        JMenuItem miSaveTemplate = new JMenuItem("Save Diagram as Template");
        JMenuItem miAboutUs = new JMenuItem("About Us");
        JMenuItem miDelete = new JMenuItem("Delete");
        JMenuItem miChangeAuthor = new JMenuItem("Change Author");
        JMenuItem miChangePath = new JMenuItem("Change Path");
        JMenuItem miGenerate = new JMenuItem("Generate Code For Project");

        miNew.addActionListener(MainFrame.getInstance().getActionManager().getNewProjectAction());
        miChangeAuthor.addActionListener(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        miAboutUs.addActionListener(MainFrame.getInstance().getActionManager().getInfoAction());
        miDelete.addActionListener(MainFrame.getInstance().getActionManager().getDeleteAction());
        miChangePath.addActionListener(MainFrame.getInstance().getActionManager().getChangePathAction());
        miSaveAsImage.addActionListener(MainFrame.getInstance().getActionManager().getScreenshotAction());
        miSave.addActionListener(MainFrame.getInstance().getActionManager().getSaveAction());
        miOpen.addActionListener(MainFrame.getInstance().getActionManager().getOpenAction());
        miSaveAs.addActionListener(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
        miSaveTemplate.addActionListener(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
        miGenerate.addActionListener(MainFrame.getInstance().getActionManager().getCodeGenerationAction());

        JSeparator seperator = new JSeparator();
        seperator.setOpaque(true);

        file.add(miNew);
        file.add(miSave);
        file.add(miSaveAs);
        file.add(miOpen);
        file.add(miSaveAsImage);
        file.add(miSaveTemplate);
        edit.add(miAboutUs);
        functions.add(miDelete);
        functions.add(miGenerate);
        edit.add(miChangeAuthor);
        edit.add(miChangePath);

        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(MainFrame.getInstance().getActionManager().getExitAction());
        file.add(miExit);

        add(file);
        add(edit);
        add(functions);
    }

}
