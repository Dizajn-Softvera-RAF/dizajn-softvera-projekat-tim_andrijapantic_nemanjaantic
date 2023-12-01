package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Connection;

import java.awt.*;

public class Composition extends Connection {
    public Composition(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent, color, lineWidth);
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }
}
