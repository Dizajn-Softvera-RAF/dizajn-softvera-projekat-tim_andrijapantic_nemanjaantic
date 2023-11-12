package app.model.tree;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class ClassyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MyNodeMutable treeItemSelected = (MyNodeMutable) path.getLastPathComponent();
        System.out.println("Selektovan cvor:" + treeItemSelected.getClassyNode().toString());
        System.out.println("getPath: " + e.getPath());
    }
}
