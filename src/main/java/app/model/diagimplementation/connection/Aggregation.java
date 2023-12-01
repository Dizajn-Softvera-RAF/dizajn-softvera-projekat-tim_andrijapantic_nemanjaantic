package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;

import java.awt.*;

public class Aggregation extends Connection {

    public Aggregation() {
    }

    public Aggregation(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent, color, lineWidth);
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }
}
