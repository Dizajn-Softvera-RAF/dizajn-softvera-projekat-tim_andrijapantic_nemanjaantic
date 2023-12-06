package app.view.painters;

import app.model.diagcomposite.DiagramElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SelectionPainter  extends ElementPainter{

    private Rectangle2D.Double shape;
    private int x = 0, y = 0;
    private int x2 = 0, y2 = 0;
    private Point startPoint, endPoint;
    private Graphics2D g2;

    private Rectangle toPaint;


    public SelectionPainter(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    @Override
    public void paint(Graphics2D g2) {

        int height = Math.abs(y - y2);
        int width = Math.abs(x - x2);
        int pX = Math.min(x, x2);
        int pY = Math.min(y, y2);
        toPaint = new Rectangle(pX, pY, width, height);
        float dash1[] = { 10.0f };
        g2.setStroke(new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
        g2.draw(toPaint);
        setToPaint(toPaint);


    }

    @Override
    public boolean elementAt(DiagramElement element, Point point) {
        return getShape().contains(point);
    }

    public Rectangle2D.Double getShape() {
        return shape;
    }

    public void setShape(Rectangle2D.Double shape) {
        this.shape = shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }


    public Rectangle getToPaint() {
        return toPaint;
    }

    public void setToPaint(Rectangle toPaint) {
        this.toPaint = toPaint;
    }
}
