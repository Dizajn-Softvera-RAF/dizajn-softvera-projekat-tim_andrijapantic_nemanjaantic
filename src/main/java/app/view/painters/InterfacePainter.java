package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InterfacePainter extends ElementPainter{

    protected Rectangle2D.Double shape;

    public InterfacePainter(Interface element) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(new Color(194,238,219));
        g2.setStroke(new BasicStroke(((Interface)getElement()).getStroke()));
        this.shape = new Rectangle2D.Double(((Interface)getElement()).getPosition().getX()-90, ((Interface)getElement()).getPosition().getY()-50, 180, 105);
        setShape(shape);

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


}
