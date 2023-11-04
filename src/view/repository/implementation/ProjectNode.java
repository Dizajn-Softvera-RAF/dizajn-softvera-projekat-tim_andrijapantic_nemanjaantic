package view.repository.implementation;

import view.repository.composite.AbstractClassyNode;
import view.repository.composite.ClassyNodeComposite;

public class ProjectNode extends ClassyNodeComposite<PackageNode> {

    public ProjectNode(String name, ProjectExplorer parent) {
        super(name, parent);
    }

    public ProjectNode() {
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof PackageNode) {
            PackageNode node = (PackageNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }
    }
}
