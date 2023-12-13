package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class CompositionPainter extends ElementPainter{
    public CompositionPainter(Composition element ) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {

        int startX = (int)((Composition)getElement()).getStartPoint().getX();
        int startY = (int)((Composition)getElement()).getStartPoint().getY();
        int endX = (int)((Composition)getElement()).getEndPoint().getX();
        int endY = (int)((Composition)getElement()).getEndPoint().getY();

        g2.setColor(element.getCurrentColor());
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line);

        double angle = Math.atan2(endY - startY, endX - startX);

        int rhombusX = startX + (int) (20 * Math.cos(angle));
        int rhombusY = startY + (int) (20 * Math.sin(angle));

        AffineTransform transform = new AffineTransform();
        transform.translate(rhombusX, rhombusY);
        transform.rotate(angle);

        Path2D path = new Path2D.Double();
        path.moveTo(0, -20);
        path.lineTo(20, 0);
        path.lineTo(0, 20);
        path.lineTo(-20, 0);
        path.closePath();

        Shape transformedShape = transform.createTransformedShape(path);
        setShape(transformedShape);

        Path2D path2 = new Path2D.Double();
        path2.moveTo(-20, -10);
        path2.lineTo(line.getP1().distance(new Point((int) (line.getP2().getX()-35), (int) (line.getP2().getY()-20))), -10);
        path2.lineTo(line.getP1().distance(new Point((int) (line.getP2().getX()-35), (int) (line.getP2().getY()-20))), 10);
        path2.lineTo(-20, 10 );
        path2.closePath();

        g2.setColor(element.getCurrentColor());
        g2.fill(getShape());
        transform.translate(0, 0);
        Shape transformedShape2 = transform.createTransformedShape(path2);
        setShape(transformedShape2);
        g2.setColor(new Color(0,0,0,0));
        g2.fill(transformedShape2);
        g2.setColor(element.getCurrentColor());
        g2.draw(transformedShape);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }
}
