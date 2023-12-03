package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.Klasa;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassPainter extends ElementPainter{
    protected Rectangle2D.Double shape;
    Klasa element;
    private Color color;

    public ClassPainter(Klasa element) {
        this.element = element;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(new Color(238,230,194));
        g2.setStroke(new BasicStroke(element.getStroke()));
        this.shape = new Rectangle2D.Double(element.getPosition().getX()-90, element.getPosition().getY()-50, 180, 105);


        g2.fill(shape);
        g2.setColor(color);
        g2.draw(shape);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString(element.getName(), (int) shape.getX()+20, (int) shape.getY()+20);


    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }


    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
