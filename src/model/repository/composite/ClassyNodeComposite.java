package model.repository.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite<T>  extends AbstractClassyNode{

    List<T> children;

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public ClassyNodeComposite() {
    }

    public ClassyNodeComposite(String name, AbstractClassyNode parent, List<T> children) {
        super(name, parent);
        this.children = children;
    }

    public ClassyNodeComposite(String name, AbstractClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<T>();
    }

    public abstract void addChild(AbstractClassyNode child);

    @Override
    public String toString() {
        return super.name;
    }
}
