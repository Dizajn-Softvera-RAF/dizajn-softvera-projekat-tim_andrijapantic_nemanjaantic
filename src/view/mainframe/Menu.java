package view.mainframe;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {

    public Menu() {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu functions = new JMenu("Functions");


        JMenuItem miNew = new JMenuItem("New");
        JMenuItem miAboutUs = new JMenuItem("About Us");
        JMenuItem miDelete = new JMenuItem("Delete");
        JMenuItem miChangeAuthor = new JMenuItem("Change Author");

        miNew.addActionListener(MainFrame.getInstance().getActionManager().getNewProjectAction());
        miChangeAuthor.addActionListener(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        miAboutUs.addActionListener(MainFrame.getInstance().getActionManager().getInfoAction());
        miDelete.addActionListener(MainFrame.getInstance().getActionManager().getDeleteAction());

        JSeparator seperator = new JSeparator();
        seperator.setOpaque(true);

        file.add(miNew);
        edit.add(miAboutUs);
        functions.add(miDelete);
        edit.add(miChangeAuthor);

        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(MainFrame.getInstance().getActionManager().getExitAction());
        file.add(miExit);

        add(file);
        add(edit);
        add(functions);
    }

}
