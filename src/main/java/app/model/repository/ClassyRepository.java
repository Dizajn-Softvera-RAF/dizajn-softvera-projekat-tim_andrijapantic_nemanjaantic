package app.model.repository;

import app.model.composite.ClassyNodeComposite;
import app.model.implementation.ProjectExplorer;

public interface ClassyRepository {

    ProjectExplorer getProjectExplorer();

    void addChild(ClassyNodeComposite parent, String child);

    ClassyNodeComposite createNode(String type, String name, ClassyNodeComposite parent);
}
