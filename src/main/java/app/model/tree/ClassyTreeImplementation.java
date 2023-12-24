package app.model.tree;

import app.core.AppCore;
import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;
import app.model.implementation.*;
import app.view.mainframe.MainFrame;
import app.view.tree.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class ClassyTreeImplementation implements ClassyTree {

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private MyNodeMutable root;

    public ClassyTreeImplementation() {
    }

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new MyNodeMutable(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }


    @Override
    public MyNodeMutable addChild(MyNodeMutable parent, ClassyNodeComposite element) {
            ClassyNodeComposite child = createChild(parent.getClassyNode());

            MyNodeMutable toReturn = new MyNodeMutable(child);

            parent.add(toReturn);
            parent.getClassyNode().addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            return toReturn;

    }

    public MyNodeMutable addExistingChild(MyNodeMutable element, ClassyNodeComposite child) {

            MyNodeMutable toReturn = new MyNodeMutable(child);
            element.add(toReturn);
            element.getClassyNode().addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            return toReturn;
    }

    public void deleteChildFromTree(MyNodeMutable node) throws NullPointerException{

        DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
        model.removeNodeFromParent(node);
    }

    private ClassyNodeComposite createChild(ClassyNodeComposite parent) {

        if (parent instanceof ProjectExplorer) {

            return AppCore.getInstance().getClassyRepository().createNode("Project", "Project" + new Random().nextInt(100), parent);
        }
        if (parent instanceof ProjectNode || (parent instanceof PackageNode && parent.isPackageCheck())) {

            return AppCore.getInstance().getClassyRepository().createNode("Package", "Package" + new Random().nextInt(100), parent);
        }
        else if (parent instanceof PackageNode) {

            return AppCore.getInstance().getClassyRepository().createNode("Diagram", "Diagram" + new Random().nextInt(1000), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("klasa")) {

            return AppCore.getInstance().getClassyRepository().createNode("Klasa", "Klasa " + new Random().nextInt(100), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("interface")) {

            return AppCore.getInstance().getClassyRepository().createNode("Interface", "Interface " + new Random().nextInt(100), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("enum")) {

            return AppCore.getInstance().getClassyRepository().createNode("Enum", "Enum " + new Random().nextInt(100), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("aggregation")) {

            return AppCore.getInstance().getClassyRepository().createNode("Aggregation", "Aggregation " + new Random().nextInt(100), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("composition")) {

            return AppCore.getInstance().getClassyRepository().createNode("Composition", "Composition " + new Random().nextInt(100), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("generalization")) {

            return AppCore.getInstance().getClassyRepository().createNode("Generalization", "Generalization" + new Random().nextInt(100), parent);
        }
        if (parent instanceof DiagramNode && MainFrame.getInstance().getChildToCreateType().equals("dependency")) {

            return AppCore.getInstance().getClassyRepository().createNode("Dependency", "Dependency" + new Random().nextInt(100), parent);
        }
        return null;
    }

    @Override
    public MyNodeMutable getSelectedNode() {
        return null;
    }

    @Override
    public void loadProject(ProjectNode node) {
        MyNodeMutable loadedProject = new MyNodeMutable(node);
        root.add(loadedProject);

        ClassyNodeComposite projectExplorer = root.getClassyNode();
        projectExplorer.addChild(node);

        addChildrenToTree(loadedProject, node);

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadTemplate(DiagramNode node) {
        node.setParent(MainFrame.getInstance().getSelectedNode().getClassyNode());
        MyNodeMutable loadedTemplate = new MyNodeMutable(node);
        MainFrame.getInstance().getSelectedNode().add(loadedTemplate);

        ClassyNodeComposite currrentPackage = MainFrame.getInstance().getSelectedNode().getClassyNode();
        currrentPackage.addChild(node);

        addChildrenToTree(loadedTemplate, node);

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void addChildrenToTree(MyNodeMutable parentNode, ClassyNodeComposite element) {
        for (Object child : element.getChildren()) {
            MyNodeMutable childNode = addExistingChild(parentNode, (ClassyNodeComposite) child);
            childNode.getClassyNode().setParent(parentNode.getClassyNode());
            parentNode.add(childNode);
            if (childNode.getClassyNode() instanceof DiagramElement) {
                ((DiagramElement) childNode.getClassyNode()).setMyNodeMutable(childNode);
            }
            addChildrenToTree(childNode, (ClassyNodeComposite) child);
        }
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
