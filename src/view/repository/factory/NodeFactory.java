package view.repository.factory;

import view.repository.composite.ClassyNodeComposite;
import view.repository.implementation.DiagramNode;
import view.repository.implementation.PackageNode;
import view.repository.implementation.ProjectExplorer;
import view.repository.implementation.ProjectNode;

public class NodeFactory extends AbstractNodeFactory{
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

