package model.repository;

import model.repository.composite.ClassyNodeComposite;
import model.repository.implementation.ProjectExplorer;

public interface ClassyRepository {

    ProjectExplorer getProjectExplorer();

    void addChild(ClassyNodeComposite parent, String child);

    ClassyNodeComposite createNode(String type, String name, ClassyNodeComposite parent);
}
