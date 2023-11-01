package view.mainframe;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar() {


        setFloatable(false);
        setBackground(new Color(169, 204, 227));
        Dimension sizeT = new Dimension(200, 50);
        this.setPreferredSize(sizeT.getSize());
        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
        addSeparator();
        add (MainFrame.getInstance().getActionManager().getNewPackageAction());
        addSeparator();
        add (MainFrame.getInstance().getActionManager().getNewComponentAction());
        addSeparator();
        add (MainFrame.getInstance().getActionManager().getInfoAction());
    }
}
