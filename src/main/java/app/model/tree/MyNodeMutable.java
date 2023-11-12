package app.model.tree;

import app.model.composite.ClassyNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;

public class MyNodeMutable extends DefaultMutableTreeNode {


    private ClassyNodeComposite classyNode;

    public MyNodeMutable(ClassyNodeComposite classyNode) {
        super(classyNode);
        this.classyNode = classyNode;
    }

    public ClassyNodeComposite getClassyNode() {
        return classyNode;
    }

    public void setClassyNode(ClassyNodeComposite classyNode) {
        this.classyNode = classyNode;
    }

}
