package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.Klasa;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassPainter extends ElementPainter{
    protected Rectangle2D.Double shape;
    Klasa element;

    public ClassPainter(Klasa element) {
        this.element = element;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(element.getStroke()));
        FontMetrics fontMetrics = g2.getFontMetrics();
        Rectangle2D textBounds = fontMetrics.getStringBounds(element.getName(), g2);
        this.shape = new Rectangle2D.Double(element.getPosition().getX(), element.getPosition().getY(), 200, 100);
        double rectCenterX = shape.getX() + shape.getWidth() / 2;
        double rectCenterY = shape.getY() + shape.getHeight() / 2;

        double textCenterX = rectCenterX - textBounds.getWidth() / 2;
        double textCenterY = rectCenterY - textBounds.getHeight() / 2;
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        g2.drawString(element.getName(), (int) textCenterX, (int) textCenterY);

    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }


    public Shape getShape() {
        return shape;
    }


}
