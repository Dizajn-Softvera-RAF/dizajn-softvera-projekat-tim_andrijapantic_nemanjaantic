package app.model.factory;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;
import app.model.implementation.*;

public class NodeFactory extends AbstractNodeFactory {
    @Override
    public ClassyNodeComposite create(String type, String name, ClassyNodeComposite parent) throws IllegalArgumentException {
        if (type.equals("Diagram")) {

            return new DiagramNode(name, parent);
        } else if (type.equals("Project")) {

            return new ProjectNode(name, (ProjectExplorer) parent);
        } else if (type.equals("Package")) {

            return new PackageNode(name, parent);

        }

        throw new IllegalArgumentException("Type not supported: " + type);
    }
}

