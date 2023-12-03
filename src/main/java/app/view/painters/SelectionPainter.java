package app.view.painters;

import app.model.diagcomposite.DiagramElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SelectionPainter  extends ElementPainter{

    private Rectangle2D.Double shape;
    private int x = 0, y = 0;
    private Point startPoint, endPoint;
    private Graphics2D g2;
    private int height = 0, width = 0;
    private Rectangle toPaint;


    public SelectionPainter(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    @Override
    public void paint(Graphics2D g2) {

        toPaint = new Rectangle((int) startPoint.getX(), (int) startPoint.getY(), height, width);
        g2.draw(toPaint);


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

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Rectangle getToPaint() {
        return toPaint;
    }

    public void setToPaint(Rectangle toPaint) {
        this.toPaint = toPaint;
    }
}
