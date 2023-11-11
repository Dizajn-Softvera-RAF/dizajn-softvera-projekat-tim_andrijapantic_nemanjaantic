package model.repository;

import model.repository.composite.ClassyNodeComposite;
import model.repository.factory.NodeFactory;
import model.repository.implementation.ProjectExplorer;

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
