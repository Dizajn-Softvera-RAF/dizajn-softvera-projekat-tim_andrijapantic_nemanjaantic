package app.model.repository.implementation;

import app.model.repository.composite.AbstractClassyNode;
import app.model.repository.composite.ClassyNodeComposite;

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

    @Override
    public void removeChildren() {

    }
}
