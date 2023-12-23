package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Generalization extends Connection {

    public Generalization(String name, ClassyNodeComposite parent) {
        super(name,parent);
    }
    public Generalization() {}

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }
}
