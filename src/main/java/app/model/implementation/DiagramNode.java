package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;

import java.util.List;

public class DiagramNode extends ClassyNodeComposite<DiagramElement> {

    private List<DiagramElement> children;

    public DiagramNode(String name, AbstractClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof DiagramElement) {
            DiagramElement node = (DiagramElement) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }

    }

    @Override
    public void removeChildren() {

    }

    @Override
    public List<DiagramElement> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<DiagramElement> children) {
        this.children = children;
    }
}
