package app.model.tree;

import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;
import app.model.implementation.DiagramNode;
import app.model.implementation.ProjectExplorer;
import app.model.implementation.ProjectNode;
import app.view.tree.ClassyTreeView;

import javax.lang.model.element.Element;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    MyNodeMutable addChild(MyNodeMutable parent, ClassyNodeComposite element);

    MyNodeMutable getSelectedNode();

    void loadProject(ProjectNode node);
    void loadDiagram(DiagramNode node);
}
