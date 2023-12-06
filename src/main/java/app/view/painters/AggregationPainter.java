package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.interclass.EnumComp;
import javafx.scene.shape.Line;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class AggregationPainter extends ElementPainter{
    private Line2D.Float shape;

    public AggregationPainter(Aggregation element ) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {
       // shape = new Line2D.Float((int)((Aggregation)getElement()).getFromInterclass().getPosition().getX(), (int)((Aggregation)getElement()).getFromInterclass().getPosition().getY(),
            //    (int)((Aggregation)getElement()).getToInterclass().getPosition().getX(), (int)((Aggregation)getElement()).getToInterclass().getPosition().getY());
        int startX = (int)((Aggregation)getElement()).getStartPoint().getX();
        int startY = (int)((Aggregation)getElement()).getStartPoint().getY();
        int endX = (int)((Aggregation)getElement()).getEndPoint().getX();
        int endY = (int)((Aggregation)getElement()).getEndPoint().getY();

        g2.drawLine(startX,startY , endX, endY);

        double angle = Math.atan2(endY - startY, endX - startX);

        int rhombusX = endX - (int) (50 * Math.cos(angle));
        int rhombusY = endY - (int) (50 * Math.sin(angle));


        AffineTransform transform = new AffineTransform();
        transform.translate(rhombusX, rhombusY);
        transform.rotate(angle);

        Path2D path = new Path2D.Double();
        path.moveTo(0, -50 / 2);
        path.lineTo(50 / 2, 0);
        path.lineTo(0, 50 / 2);
        path.lineTo(-50 / 2, 0);
        path.closePath();

        Shape transformedShape = transform.createTransformedShape(path);
        Rectangle bounds = transformedShape.getBounds();
        transform.translate(-bounds.getWidth() / 2, -bounds.getHeight() / 2);

        g2.draw(transformedShape);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

    public Line2D.Float getShape() {
        return shape;
    }


}
