package model.repository.implementation;

import model.repository.composite.AbstractClassyNode;
import model.repository.composite.ClassyNodeComposite;

public class ElementNode<T> extends ClassyNodeComposite<Void> {

    public T element;

    public ElementNode() {
    }

    public ElementNode(String name, AbstractClassyNode parent) {
        super(name, parent);
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }


}

