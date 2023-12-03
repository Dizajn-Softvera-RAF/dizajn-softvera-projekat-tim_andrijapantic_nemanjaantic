package app.view.mainframe;

import app.controller.MouseController;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.DiagramElement;
import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.implementation.DiagramNode;
import app.model.tree.MyNodeMutable;
import app.view.painters.*;
import app.view.tabs.Tab;
import app.view.tabs.TabbedPane;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber {
    static final int xOffset = 30, yOffset = 30;
    double MAX_ZOOM = 2.0;
    double MIN_ZOOM = 0.3;
    double scale = 1.0;
    int openFrameCount = 0;

    int deltaX = 0;
    int deltaY = 0;

    private Tab tab;
    private DiagramNode diagramNode;
    private MyNodeMutable myNodeMutable;
    private MouseController mouseController;
    private PackageView parentView;
    private ArrayList<ElementPainter> elementPainters;
    private Connection currentLink;

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
        if (notification.getType().equals(NotificationType.EXISTING_TAB_OPENED)) {

            for (DiagramElement element: getDiagramNode().getChildren()) {

                if (element instanceof Klasa) {
                    getElementPainters().add(new ClassPainter((Klasa)element));
                    System.out.println("Dodao sam element: " + element.getName());
                } else if(element instanceof Interface){
                    getElementPainters().add(new InterfacePainter((Interface)element));
                    System.out.println("Dodao sam element: " + element.getName());
                } else if(element instanceof EnumComp){
                    getElementPainters().add(new EnumPainter((EnumComp)element));
                    System.out.println("Dodao sam element: " + element.getName());
                } else if(element instanceof Aggregation){
                    getElementPainters().add(0, new AggregationPainter((Aggregation)element));
                    System.out.println("Dodao sam element: " + element.getName());
                }

            }

        }
        repaint();
        System.out.println("Pozvao sam repaint");
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
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(deltaX, deltaY);
        affineTransform.scale(scale, scale);
        g2.transform(affineTransform);
        for (ElementPainter e : elementPainters) {
            e.paint(g2);
            System.out.println("Repaintam element: " + e);
            System.out.println("Repaintam element cije je ime: " + e.getName());

        }

    }

    public ArrayList<ElementPainter> getElementPainters() {
        return elementPainters;
    }

    public void setElementPainters(ArrayList<ElementPainter> elementPainters) {
        this.elementPainters = elementPainters;
    }

    public Connection getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(Connection currentLink) {
        this.currentLink = currentLink;
    }

    public void setZoomIn() {
        if (scale < MAX_ZOOM)
            scale *= 1.2;
        if (scale > MAX_ZOOM)
            scale = MAX_ZOOM;
        repaint();
    }
    public void setZoomOut() {
        if (scale > MIN_ZOOM)
            scale /= 1.2;
        if (scale < MIN_ZOOM)
            scale = MIN_ZOOM;
        repaint();
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Point getAbsolutePoint(int x, int y) {
        return new Point((int) ((x-getDeltaX())/getScale()), (int) ((y-getDeltaY())/getScale()));
    }

    public MyNodeMutable getMyNodeMutable() {
        return myNodeMutable;
    }

    public void setMyNodeMutable(MyNodeMutable myNodeMutable) {
        this.myNodeMutable = myNodeMutable;
    }
}
