package model.tree;

import model.core.AppCore;
import view.repository.composite.ClassyNodeComposite;
import view.repository.implementation.*;
import view.tree.ClassyTreeView;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private MyNodeMutable root;
    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new MyNodeMutable(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public MyNodeMutable addChild(MyNodeMutable parent, Element element, String name) {

        ClassyNodeComposite child = createChild(parent.getClassyNode(), name);
        if (child instanceof ElementNode) {

            ElementNode elementNode = (ElementNode) child;
            elementNode.setElement(element);



            MyNodeMutable toReturn = new MyNodeMutable(elementNode);
            parent.add(toReturn);
            parent.getClassyNode().setName(elementNode.getName());
            parent.getClassyNode().addChild(elementNode);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            return toReturn;


        } else {
            MyNodeMutable toReturn = new MyNodeMutable(child);

            parent.add(toReturn);
            parent.getClassyNode().addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            return toReturn;
        }
    }

    private ClassyNodeComposite createChild(ClassyNodeComposite parent, String name) {

        if (parent instanceof ProjectExplorer) {

            return AppCore.getInstance().getClassyRepository().createNode("Project", name, parent);
        }
        if (parent instanceof ProjectNode) {

            return AppCore.getInstance().getClassyRepository().createNode("Package", name, parent);
        }
        if (parent instanceof PackageNode) {

            return AppCore.getInstance().getClassyRepository().createNode("Diagram", name, parent);
        }
        if(parent instanceof DiagramNode){

            return AppCore.getInstance().getClassyRepository().createNode("Element", name, parent);
        }
        return null;
    }

    @Override
    public MyNodeMutable getSelectedNode() {
        return null;
    }

    @Override
    public void loadProject(ProjectNode node) {

    }
}
