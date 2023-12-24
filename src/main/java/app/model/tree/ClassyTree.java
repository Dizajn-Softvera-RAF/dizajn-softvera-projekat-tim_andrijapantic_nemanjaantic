package app.model.tree;

import app.model.composite.ClassyNodeComposite;
import app.model.implementation.DiagramNode;
import app.model.implementation.ProjectExplorer;
import app.model.implementation.ProjectNode;
import app.view.tree.ClassyTreeView;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    MyNodeMutable addChild(MyNodeMutable parent, ClassyNodeComposite element);

    MyNodeMutable getSelectedNode();

    void loadProject(ProjectNode node);
    void loadTemplate(DiagramNode node);
}
