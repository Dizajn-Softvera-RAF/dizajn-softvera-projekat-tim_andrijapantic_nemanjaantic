package view.mainframe;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {

    public Menu() {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('F');
        JMenu functions = new JMenu("Functions");
        functions.setMnemonic('F');

        JMenuItem miNew = new JMenuItem("New");
        JMenuItem miAboutUs = new JMenuItem("About Us");
        JMenuItem miDelete = new JMenuItem("Delete");

        JSeparator seperator = new JSeparator();
        seperator.setOpaque(true);

        file.add(miNew);
        edit.add(miAboutUs);
        functions.add(miDelete);

        JMenuItem miOpen = new JMenuItem("Open");
        JMenuItem miClose = new JMenuItem("Close Window");
        JMenuItem miCloseAll = new JMenuItem("Close All");

        add(file);
        add(edit);
        add(functions);
    }

}
