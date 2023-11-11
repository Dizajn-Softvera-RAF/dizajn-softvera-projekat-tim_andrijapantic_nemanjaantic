package model.tree;

import model.repository.composite.ClassyNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

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
