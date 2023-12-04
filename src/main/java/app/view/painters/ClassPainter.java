package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.Klasa;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassPainter extends ElementPainter{
    protected Rectangle2D.Double shape;

    public ClassPainter(Klasa element) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(new Color(238,230,194));
        g2.setStroke(new BasicStroke(((Klasa)getElement()).getStroke()));
        this.shape = new Rectangle2D.Double(((Klasa)getElement()).getPosition().getX()-90, ((Klasa)getElement()).getPosition().getY()-50, 180, 105);
        setShape(shape);

        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        //g2.drawLine((int) shape.getX(), (int) shape.getY()+25, 180, 105);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString(element.getName(), (int) shape.getX()+20, (int) shape.getY()+20);


    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }




}
