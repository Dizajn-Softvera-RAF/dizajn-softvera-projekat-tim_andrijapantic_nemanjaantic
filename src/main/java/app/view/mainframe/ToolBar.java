package app.view.mainframe;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar() {


        setFloatable(false);
        setBackground(new Color(169, 204, 227));
        Dimension sizeT = new Dimension(200, 50);
        this.setPreferredSize(sizeT.getSize());
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getOpenAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getChangePathAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getScreenshotAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getCodeGenerationAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getExitAction());
    }
}
