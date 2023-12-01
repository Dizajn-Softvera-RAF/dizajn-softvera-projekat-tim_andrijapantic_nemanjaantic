package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;

import java.util.List;

public abstract class DiagramElement extends ClassyNodeComposite<Void> {

    public DiagramElement() {
    }

    public DiagramElement(String name, AbstractClassyNode parent, List<Void> children) {
        super(name, parent, children);
    }

    public DiagramElement(String name, AbstractClassyNode parent) {
        super(name, parent);
    }
}
