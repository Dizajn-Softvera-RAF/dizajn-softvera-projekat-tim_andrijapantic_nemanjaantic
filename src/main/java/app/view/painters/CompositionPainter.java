package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;

import java.awt.*;
import java.awt.geom.AffineTransform;
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

        g2.setColor(Color.BLACK);
        g2.drawLine(startX,startY , endX, endY);


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
        Rectangle bounds = transformedShape.getBounds();
        transform.translate(-bounds.getWidth() / 2, -bounds.getHeight() / 2);
        setShape(transformedShape);
        g2.setColor(Color.BLACK);
        g2.fill(getShape());
        g2.setColor(Color.BLACK);
        g2.draw(transformedShape);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }
}
