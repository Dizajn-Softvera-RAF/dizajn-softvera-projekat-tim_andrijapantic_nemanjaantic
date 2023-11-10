package model.tree;

import core.AppCore;
import model.repository.composite.ClassyNodeComposite;
import model.repository.implementation.*;
import view.tree.ClassyTreeView;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private MyNodeMutable root;

    public ClassyTreeImplementation() {}

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new MyNodeMutable(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }




    @Override
    public MyNodeMutable addChild(MyNodeMutable parent, Element element) {
        ClassyNodeComposite child = createChild(parent.getClassyNode());
        if (child instanceof ElementNode) {

            ElementNode elementNode = (ElementNode) child;
            elementNode.setElement(element);



            MyNodeMutable toReturn = new MyNodeMutable(elementNode);
            parent.add(toReturn);
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

    private ClassyNodeComposite createChild(ClassyNodeComposite parent) {

        if (parent instanceof ProjectExplorer) {

            return AppCore.getInstance().getClassyRepository().createNode("Project", "Project" + new Random().nextInt(100), parent);
        }
        if (parent instanceof ProjectNode || parent.isPackageCheck()) {

            return AppCore.getInstance().getClassyRepository().createNode("Package", "Package" + new Random().nextInt(100), parent);
        }
        if (parent instanceof PackageNode) {

            return AppCore.getInstance().getClassyRepository().createNode("Diagram", "Diagram" + new Random().nextInt(1000), parent);
        }
        if(parent instanceof DiagramNode){

            return AppCore.getInstance().getClassyRepository().createNode("Element", "Element" + new Random().nextInt(100), parent);
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

    public ClassyTreeView getTreeView() {
        return treeView;
    }

    public void setTreeView(ClassyTreeView treeView) {
        this.treeView = treeView;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
    }
}
