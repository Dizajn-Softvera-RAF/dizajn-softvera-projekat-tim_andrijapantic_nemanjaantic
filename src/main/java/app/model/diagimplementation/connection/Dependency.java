package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;
import app.model.diagimplementation.connection.editcomponents.Dependencies;

import java.awt.*;
import java.util.ArrayList;

public class Dependency extends Connection {

    ArrayList<Dependencies> dependencies;

    public Dependency(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent, color, lineWidth);
        dependencies = new ArrayList<>();
    }

    public Dependency(String name, ClassyNodeComposite parent) {
        super(name,parent);
        dependencies = new ArrayList<>();
    }
    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }

    public ArrayList<Dependencies> getDependencies() {
        return dependencies;
    }

    public void setDependencies(ArrayList<Dependencies> dependencies) {
        this.dependencies = dependencies;
    }
}
