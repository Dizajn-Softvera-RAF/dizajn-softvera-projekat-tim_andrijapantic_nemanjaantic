package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

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
        g2.drawLine(startX,startY , endX, endY);

        double angle = Math.atan2(endY - startY, endX - startX);

        int triangleX = endX - (int) (30 * Math.cos(angle));
        int triangleY = endY - (int) (30 * Math.sin(angle)) ;

        Path2D path = new Path2D.Double();
        path.moveTo(0, -15);
        path.lineTo(15, 15);
        path.lineTo(-15, 15);
        path.closePath();


        AffineTransform transform = AffineTransform.getTranslateInstance(triangleX, triangleY);
        transform.rotate(angle + Math.PI + Math.toRadians(45));
        setShape(transform.createTransformedShape(path));

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.WHITE);
        g2.fill(getShape());
        g2.setColor(Color.BLACK);
        g2.draw(getShape());


    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }
}
