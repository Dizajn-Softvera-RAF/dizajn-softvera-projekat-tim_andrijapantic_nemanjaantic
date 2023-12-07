package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Generalization;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

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

        g2.setColor(Color.BLACK);
        g2.drawLine(startX,startY , endX, endY);

        double angle = Math.atan2(endY - startY, endX - startX);

        int triangleX = endX - (int) (40 * Math.cos(angle));
        int triangleY = endY - (int) (40 * Math.sin(angle)) ;

        Path2D path = new Path2D.Double();
        path.moveTo(0, -20);
        path.lineTo(20, 20);
        path.lineTo(-20, 20);
        path.closePath();


        AffineTransform transform = AffineTransform.getTranslateInstance(triangleX, triangleY);
        transform.rotate(angle + Math.PI + Math.toRadians(45));
        setShape(transform.createTransformedShape(path));

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
