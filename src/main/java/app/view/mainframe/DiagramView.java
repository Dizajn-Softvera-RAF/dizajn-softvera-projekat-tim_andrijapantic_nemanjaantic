package app.view.mainframe;

import app.controller.MouseController;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.implementation.DiagramNode;
import app.view.tabs.Tab;
import app.view.tabs.TabbedPane;

import javax.swing.*;
import java.awt.*;

public class DiagramView extends JPanel implements ISubscriber {
    static final int xOffset = 30, yOffset = 30;
    int openFrameCount = 0;

    private Tab tab;
    private DiagramNode diagramNode;
    private MouseController mouseController;
    private PackageView parentView;
    //Treba da ima listu Paintera

    public DiagramView(Tab tab) {
        super();
        this.tab = tab;
        parentView = TabbedPane.getInstance().getPackageView();
        ++openFrameCount;
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

        setSize(700, 700);
        setVisible(true);
        mouseController = new MouseController(this);
        this.addMouseListener(mouseController);
        this.addMouseMotionListener(mouseController);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);

    }

    @Override
    public void update(Notification notification) {

    }

    public DiagramNode getDiagramNode() {
        return diagramNode;
    }

    public void setDiagramNode(DiagramNode diagramNode) {
        this.diagramNode = diagramNode;
    }

    public MouseController getMouseController() {
        return mouseController;
    }

    public void setMouseController(MouseController mouseController) {
        this.mouseController = mouseController;
    }

    public PackageView getParentView() {
        return parentView;
    }

    public void setParentView(PackageView parentView) {
        this.parentView = parentView;
    }
}
