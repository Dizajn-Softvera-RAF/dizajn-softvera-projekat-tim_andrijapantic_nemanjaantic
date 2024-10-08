package app.model.tree;

import app.model.implementation.DiagramNode;
import app.model.implementation.PackageNode;
import app.model.implementation.ProjectExplorer;
import app.model.implementation.ProjectNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;
        if (((MyNodeMutable) value).getClassyNode() instanceof ProjectNode) {
            imageURL = getClass().getResource("/images/projectImage.png");
        } else if (((MyNodeMutable) value).getClassyNode() instanceof DiagramNode) {
            imageURL = getClass().getResource("/images/diagramImage.png");
        } else if (((MyNodeMutable) value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/projectExplorer.png");
        } else if (((MyNodeMutable) value).getClassyNode() instanceof PackageNode) {
            imageURL = getClass().getResource("/images/packageImage.png");
        }
        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
