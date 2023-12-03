package app.model.factory;

import app.model.composite.ClassyNodeComposite;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
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

        } else if (type.equals("Klasa")) {

            return new Klasa(name, parent);

        } else if (type.equals("Interface")) {

            return new Interface(name, parent);

        } else if (type.equals("Enum")) {

            return new EnumComp(name, parent);
        } else if (type.equals("Aggregation")) {

            return new Aggregation(name, parent);
        }

        throw new IllegalArgumentException("Type not supported: " + type);
    }
}

