package view.mainframe;

import view.tabs.Tab;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramView extends JPanel {
    int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    JLabel projectName;

    private Tab tab;

    public DiagramView(Tab tab) {
        super();
        this.tab = tab;

        ++openFrameCount;
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

        /**
         * TODO
         * projectTitle negde
         * kao i autor
         */
        setSize(700, 700);
        setVisible(true);


        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);

    }
}
