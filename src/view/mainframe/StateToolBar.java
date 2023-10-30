package view.mainframe;

import javax.swing.*;
import java.awt.*;

public class StateToolBar extends JToolBar {

    public StateToolBar() {

        setOrientation(1);


        Dimension sizeT = new Dimension(40, 60);
        Dimension sizeButton = new Dimension(60, 60);

        this.setPreferredSize(sizeT.getSize());

        setFloatable(false);
        setBackground(new Color(255, 255, 204));
    }
}
