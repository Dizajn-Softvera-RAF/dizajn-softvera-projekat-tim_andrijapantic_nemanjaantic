package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class DependencyPainter extends ElementPainter{
    public DependencyPainter(Dependency element ) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {

        int startX = (int)((Dependency)getElement()).getStartPoint().getX();
        int startY = (int)((Dependency)getElement()).getStartPoint().getY();
        int endX = (int)((Dependency)getElement()).getEndPoint().getX();
        int endY = (int)((Dependency)getElement()).getEndPoint().getY();


        float[] dashPattern = {5.0f, 5.0f};
        BasicStroke dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f);
        g2.setStroke(dashedStroke);
        g2.setColor(Color.BLACK);
        Line2D line = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(line);

        double angle = Math.atan2(endY - startY, endX - startX);


        int triangleX = endX - (int) (30 * Math.cos(angle));
        int triangleY = endY - (int) (30 * Math.sin(angle));

        Path2D path = new Path2D.Double();
        path.moveTo(0, -15);
        path.lineTo(15, 15);
        path.lineTo(-15, 15);
        path.closePath();


        AffineTransform transform = new AffineTransform();
        transform.translate(triangleX, triangleY);
        transform.rotate(angle);
        Shape transformedShape = transform.createTransformedShape(path);
        setShape(transformedShape);

        transform.translate(0, 0);

        Path2D path2 = new Path2D.Double();
        path2.moveTo(-triangleX, -10);
        path2.lineTo(line.getP1().distance(new Point((int) (line.getP2().getX()), (int) (line.getP2().getY())))/15, -10);
        path2.lineTo(line.getP1().distance(new Point((int) (line.getP2().getX()), (int) (line.getP2().getY())))/15, 10);
        path2.lineTo(-triangleX, 10);
        path2.closePath();

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.WHITE);
        g2.fill(getShape());
        g2.setColor(new Color(0,0,0,0));
        Shape transformedShape2 = transform.createTransformedShape(path2);
        setShape(transformedShape2);
        g2.fill(transformedShape2);
        g2.setColor(Color.BLACK);
        g2.draw(transformedShape);

    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }
}
