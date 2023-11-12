package app.model.repository.factory;

import app.model.repository.composite.ClassyNodeComposite;
import app.model.repository.implementation.*;

public class NodeFactory extends AbstractNodeFactory {
    @Override
    public ClassyNodeComposite create(String type, String name, ClassyNodeComposite parent) throws IllegalArgumentException {
        if (type.equals("Diagram")) {

            return new DiagramNode(name, parent);
        } else if (type.equals("Project")) {

            return new ProjectNode(name, (ProjectExplorer) parent);
        } else if (type.equals("Package")) {

            return new PackageNode(name, parent);

        } else if (type.equals("Element")) {
            return new ElementNode(name, parent);
        }

        throw new IllegalArgumentException("Type not supported: " + type);
    }
}

