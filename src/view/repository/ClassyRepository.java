package view.repository;

import view.repository.composite.ClassyNodeComposite;
import view.repository.implementation.ProjectExplorer;

public interface ClassyRepository {

    ProjectExplorer getProjectExplorer();

    void addChild(ClassyNodeComposite parent, String child);

    ClassyNodeComposite createNode(String type, String name, ClassyNodeComposite parent);
}
