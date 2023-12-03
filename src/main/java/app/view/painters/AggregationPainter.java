package app.view.painters;

import app.model.diagcomposite.DiagramElement;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.interclass.EnumComp;
import javafx.scene.shape.Line;


import java.awt.*;
import java.awt.geom.Line2D;

public class AggregationPainter extends ElementPainter{
    private Line2D.Float shape;

    public AggregationPainter(Aggregation element ) {
        setElement(element);
        this.setName(element.getName());
        setShape(shape);
    }

    @Override
    public void paint(Graphics2D g2) {
        shape = new Line2D.Float((int)((Aggregation)getElement()).getFromInterclass().getPosition().getX(), (int)((Aggregation)getElement()).getFromInterclass().getPosition().getY(),
                (int)((Aggregation)getElement()).getToInterclass().getPosition().getX(), (int)((Aggregation)getElement()).getToInterclass().getPosition().getY());
        setShape(shape);
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


}
