package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class EnumPainter extends ElementPainter{

    protected Rectangle2D.Double shape;

    public EnumPainter(EnumComp element) {
        setElement(element);
        this.setName(element.getName());
    }


    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(new Color(172,170,219));
        g2.setStroke(new BasicStroke(((EnumComp)getElement()).getStroke()));
        this.shape = new Rectangle2D.Double(((EnumComp)getElement()).getPosition().getX()-90, ((EnumComp)getElement()).getPosition().getY()-50, 180, 100);


        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString(element.getName(), (int) shape.getX()+20, (int) shape.getY()+20);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

    public Rectangle2D.Double getShape() {
        return shape;
    }

    public void setShape(Rectangle2D.Double shape) {
        this.shape = shape;
    }
}
