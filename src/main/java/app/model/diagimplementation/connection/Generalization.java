package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;

import java.awt.*;

public class Generalization extends Connection {

    public Generalization(String name, ClassyNodeComposite parent) {
        super(name,parent);
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }
}
