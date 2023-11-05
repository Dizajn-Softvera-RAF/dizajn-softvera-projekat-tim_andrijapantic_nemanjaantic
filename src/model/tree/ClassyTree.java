package model.tree;

import view.repository.implementation.ProjectExplorer;
import view.repository.implementation.ProjectNode;
import view.tree.ClassyTreeView;

import javax.lang.model.element.Element;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    MyNodeMutable addChild(MyNodeMutable parent, Element element);

    MyNodeMutable getSelectedNode();
    void loadProject(ProjectNode node);
}
