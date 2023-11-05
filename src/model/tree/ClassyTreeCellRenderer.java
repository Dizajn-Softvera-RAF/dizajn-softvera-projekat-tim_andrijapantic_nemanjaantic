package model.tree;

import view.repository.implementation.DiagramNode;
import view.repository.implementation.ProjectExplorer;
import view.repository.implementation.ProjectNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer  extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;
        if (((MyNodeMutable) value).getClassyNode() instanceof ProjectNode) {
            imageURL = getClass().getResource("/projectImage.png");
        } else if (((MyNodeMutable) value).getClassyNode() instanceof DiagramNode) {
            imageURL = getClass().getResource("/mapImage.png");
        } else if (((MyNodeMutable) value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/projectExplorer.png");
        }
        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
