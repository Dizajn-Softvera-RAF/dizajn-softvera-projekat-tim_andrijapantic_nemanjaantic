package model.repository.implementation;

import model.repository.composite.AbstractClassyNode;
import model.repository.composite.ClassyNodeComposite;

public class DiagramNode extends ClassyNodeComposite<ElementNode> {

    public DiagramNode(String name, AbstractClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof ElementNode) {
            ElementNode node = (ElementNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }

    }
}
