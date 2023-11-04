package view.repository.implementation;

import view.repository.composite.AbstractClassyNode;
import view.repository.composite.ClassyNodeComposite;

public class PackageNode extends ClassyNodeComposite<DiagramNode> {

    public PackageNode() {
    }

    public PackageNode(String name, AbstractClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof DiagramNode) {
            DiagramNode node = (DiagramNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }
    }
}
