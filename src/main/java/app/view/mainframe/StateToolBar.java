package app.view.mainframe;

import javax.swing.*;
import java.awt.*;

public class StateToolBar extends JToolBar {

    public StateToolBar() {

        setOrientation(1);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        Dimension sizeT = new Dimension(60, 60);

        add(MainFrame.getInstance().getActionManager().getIntoAddInterclassState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoAddConnectionState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoSelectionState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoEditState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoDeleteState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoMoveState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoZoomInState());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getIntoZoomOutState());

        this.setPreferredSize(sizeT.getSize());

        setFloatable(false);
        setBackground(new Color(169, 204, 227));


    }
}
