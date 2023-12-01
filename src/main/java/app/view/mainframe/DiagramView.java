package app.view.mainframe;

import app.controller.MouseController;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.implementation.DiagramNode;
import app.view.painters.ElementPainter;
import app.view.tabs.Tab;
import app.view.tabs.TabbedPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber {
    static final int xOffset = 30, yOffset = 30;
    int openFrameCount = 0;

    private Tab tab;
    private DiagramNode diagramNode;
    private MouseController mouseController;
    private PackageView parentView;
    private ArrayList<ElementPainter> elementPainters;

    public DiagramView(Tab tab) {
        super();
        this.tab = tab;
        this.elementPainters = new ArrayList<>();
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
        repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (ElementPainter e : elementPainters) {
            e.paint(g2);
        }

    }

    public ArrayList<ElementPainter> getElementPainters() {
        return elementPainters;
    }

    public void setElementPainters(ArrayList<ElementPainter> elementPainters) {
        this.elementPainters = elementPainters;
    }
}
