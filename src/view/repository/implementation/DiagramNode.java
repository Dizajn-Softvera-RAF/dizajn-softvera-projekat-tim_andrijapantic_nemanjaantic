package view.repository.implementation;

import view.repository.composite.AbstractClassyNode;
import view.repository.composite.ClassyNodeComposite;

import java.util.List;

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
