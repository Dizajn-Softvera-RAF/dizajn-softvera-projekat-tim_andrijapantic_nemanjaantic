package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Generalization;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class GeneralizationPainter extends ElementPainter{
    public GeneralizationPainter(Generalization element ) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {

        int startX = (int)((Generalization)getElement()).getStartPoint().getX();
        int startY = (int)((Generalization)getElement()).getStartPoint().getY();
        int endX = (int)((Generalization)getElement()).getEndPoint().getX();
        int endY = (int)((Generalization)getElement()).getEndPoint().getY();

        g2.setColor(element.getCurrentColor());
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line);

        double angle = Math.atan2(endY - startY, endX - startX);

        int triangleX = endX - (int) (25 * Math.cos(angle));
        int triangleY = endY - (int) (25 * Math.sin(angle));

        Path2D path = new Path2D.Double();
        path.moveTo(0, -20);
        path.lineTo(20, 20);
        path.lineTo(-20, 20);
        path.closePath();

        Rectangle2D whiteRect = new Rectangle2D.Double(-10, -5, 60, 10);

        AffineTransform transform = AffineTransform.getTranslateInstance(triangleX, triangleY);
        transform.rotate(angle);
        Shape transformedShape = transform.createTransformedShape(path);
        setShape(transformedShape);

        Shape transformedRect = transform.createTransformedShape(whiteRect);

        transform.translate(0, 0);

        Path2D path2 = new Path2D.Double();
        path2.moveTo(-triangleX, -10);
        path2.lineTo(line.getP1().distance(new Point((int) (line.getP2().getX()), (int) (line.getP2().getY())))/15, -10);
        path2.lineTo(line.getP1().distance(new Point((int) (line.getP2().getX()), (int) (line.getP2().getY())))/15, 10);
        path2.lineTo(-triangleX, 10);
        path2.closePath();

        g2.setColor(Color.WHITE);
        g2.fill(transformedRect);
        g2.fill(getShape());
        g2.setColor(new Color(0,0,0,0));
        Shape transformedShape2 = transform.createTransformedShape(path2);
        setShape(transformedShape2);
        g2.fill(transformedShape2);
        g2.setColor(element.getCurrentColor());
        g2.draw(transformedShape);

    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

}
