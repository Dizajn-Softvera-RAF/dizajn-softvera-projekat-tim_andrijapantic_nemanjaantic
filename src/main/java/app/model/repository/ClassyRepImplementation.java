package app.model.repository;

import app.model.composite.ClassyNodeComposite;
import app.model.factory.NodeFactory;
import app.model.implementation.ProjectExplorer;

public class ClassyRepImplementation implements ClassyRepository {

    private ProjectExplorer projectExplorer;

    private NodeFactory nodeFactory;

    public ClassyRepImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
        nodeFactory = new NodeFactory();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(ClassyNodeComposite parent, String child) {

    }

    @Override
    public ClassyNodeComposite createNode(String type, String name, ClassyNodeComposite parent) {
        return nodeFactory.create(type, name, parent);
    }
}
