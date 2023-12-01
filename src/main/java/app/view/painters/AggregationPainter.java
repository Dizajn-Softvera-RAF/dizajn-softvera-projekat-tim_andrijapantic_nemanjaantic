package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Aggregation;
import javafx.scene.shape.Line;


import java.awt.*;
import java.awt.geom.Line2D;

public class AggregationPainter extends ElementPainter{
    private Aggregation element;
    private Line2D.Float shape;

    public AggregationPainter(Aggregation element ) {
        this.element = element;
    }

    @Override
    public void paint(Graphics2D g2) {
        shape = new Line2D.Float((int)element.getFromInterclass().getPosition().getX(), (int)element.getFromInterclass().getPosition().getY(),
                (int)element.getToInterclass().getPosition().getX(), (int)element.getToInterclass().getPosition().getY());

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3));

        g2.fill(shape);
        g2.draw(shape);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return getShape().contains(pos);
    }

    public Line2D.Float getShape() {
        return shape;
    }

    public void setShape(Line2D.Float shape) {
        this.shape = shape;
    }

    @Override
    public Aggregation getElement() {
        return element;
    }

    public void setElement(Aggregation element) {
        this.element = element;
    }
}
