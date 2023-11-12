package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;

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
