package app.view.mainframe;

import app.view.tabs.Tab;

import javax.swing.*;
import java.awt.*;

public class DiagramView extends JPanel {
    static final int xOffset = 30, yOffset = 30;
    int openFrameCount = 0;

    private Tab tab;

    public DiagramView(Tab tab) {
        super();
        this.tab = tab;

        ++openFrameCount;
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

        setSize(700, 700);
        setVisible(true);


        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);

    }
}
