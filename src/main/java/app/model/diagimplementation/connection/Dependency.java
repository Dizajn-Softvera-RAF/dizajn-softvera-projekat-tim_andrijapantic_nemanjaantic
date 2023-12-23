package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;
import app.model.diagimplementation.connection.editcomponents.Dependencies;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Dependency extends Connection {

    ArrayList<Dependencies> dependencies;

    public Dependency(String name, ClassyNodeComposite parent) {
        super(name,parent);
        dependencies = new ArrayList<>();
    }

    public Dependency() {}
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
