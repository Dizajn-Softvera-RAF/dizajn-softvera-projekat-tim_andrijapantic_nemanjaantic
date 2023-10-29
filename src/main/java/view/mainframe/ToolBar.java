package view.mainframe;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar() {


        setFloatable(false);
        setBackground(new Color(255, 255, 204));
        Dimension sizeT = new Dimension(200, 35);
        this.setPreferredSize(sizeT.getSize());

    }
}
