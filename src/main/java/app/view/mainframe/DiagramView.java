package app.view.mainframe;

import app.controller.MouseController;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;
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
        parentView = MainFrame.getInstance().getPackageView();
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
                    element.addSubscriber(this);
                } else if(element instanceof Interface){
                    getElementPainters().add(new InterfacePainter((Interface)element));
                    element.addSubscriber(this);
                } else if(element instanceof EnumComp){
                    getElementPainters().add(new EnumPainter((EnumComp)element));
                    element.addSubscriber(this);
                } else if(element instanceof Aggregation){
                    getElementPainters().add(0, new AggregationPainter((Aggregation)element));
                    element.addSubscriber(this);
                } else if(element instanceof Composition){
                    getElementPainters().add(0, new CompositionPainter((Composition) element));
                    element.addSubscriber(this);
                } else if(element instanceof Generalization){
                    getElementPainters().add(0, new GeneralizationPainter((Generalization) element));
                    element.addSubscriber(this);
                } else if(element instanceof Dependency){
                    getElementPainters().add(0, new DependencyPainter((Dependency) element));
                    element.addSubscriber(this);
                }

            }

        }
        if (notification.getType().equals(NotificationType.PAINTER_POSITION_CHANGED)) {
            for (ElementPainter e : elementPainters) {
                if (e.getElement() instanceof Connection) {
                    Point[] points = getTwoClosestPoints(((Connection) e.getElement()).getFromInterclass().getConnectionsDots(), ((Connection) e.getElement()).getToInterclass().getConnectionsDots());
                    System.out.println(points);
                    System.out.println(points[0]);
                    System.out.println(points[1]);
                    ((Connection) e.getElement()).setStartPoint(points[0]);
                    ((Connection) e.getElement()).setEndPoint(points[1]);
                }
            }
        }
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
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(deltaX, deltaY);
        affineTransform.scale(scale, scale);
        g2.transform(affineTransform);
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
        repaint();
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
        repaint();
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

    public Point[] getTwoClosestPoints(ArrayList<Point> dots1, ArrayList<Point> dots2) {
        Point[] najblizeDve = new Point[2];
        double min = Double.MAX_VALUE;

        for (Point dot1 : dots1) {
            for (Point dot2 : dots2) {
                double razdaljina = razdaljina(dot1, dot2);
                if (razdaljina < min) {
                    min = razdaljina;
                    najblizeDve[0] = dot1;
                    najblizeDve[1] = dot2;
                }
            }
        }

        return najblizeDve;
    }

    private static double razdaljina(Point pos1, Point pos2) {
        double xDistance = Math.abs(pos2.getX() - pos1.getX());
        double yDistance = Math.abs(pos2.getY() - pos1.getY());
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }
}
