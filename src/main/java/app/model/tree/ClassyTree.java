package app.model.tree;

import app.model.implementation.ProjectExplorer;
import app.model.implementation.ProjectNode;
import app.view.tree.ClassyTreeView;

import javax.lang.model.element.Element;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    MyNodeMutable addChild(MyNodeMutable parent, Element element);

    MyNodeMutable getSelectedNode();

    void loadProject(ProjectNode node);
}
