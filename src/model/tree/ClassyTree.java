package model.tree;

import model.repository.implementation.ProjectExplorer;
import model.repository.implementation.ProjectNode;
import view.tree.ClassyTreeView;

import javax.lang.model.element.Element;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    MyNodeMutable addChild(MyNodeMutable parent, Element element);

    MyNodeMutable getSelectedNode();

    void loadProject(ProjectNode node);
}
