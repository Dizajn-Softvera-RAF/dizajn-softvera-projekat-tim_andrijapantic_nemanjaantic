package app.model.tree;

import app.core.AppCore;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;
import app.model.implementation.*;
import app.view.mainframe.MainFrame;
import app.view.tree.ClassyTreeView;

import javax.lang.model.element.Element;
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
    public MyNodeMutable addChild(MyNodeMutable parent, DiagramElement element) {
            ClassyNodeComposite child = createChild(parent.getClassyNode());

            MyNodeMutable toReturn = new MyNodeMutable(child);

            parent.add(toReturn);
            parent.getClassyNode().addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            return toReturn;

    }

    public MyNodeMutable addDiagramElementChild(MyNodeMutable diagram, DiagramElement element) {

            MyNodeMutable toReturn = new MyNodeMutable(element);
            diagram.add(toReturn);
            diagram.getClassyNode().addChild(element);
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
        if (parent instanceof ProjectNode || parent.isPackageCheck()) {

            return AppCore.getInstance().getClassyRepository().createNode("Package", "Package" + new Random().nextInt(100), parent);
        }
        if (parent instanceof PackageNode) {

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

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadDiagram(DiagramNode node) {

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
