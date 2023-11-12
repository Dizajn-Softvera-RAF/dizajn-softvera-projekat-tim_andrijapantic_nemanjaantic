package app.model.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite<T> extends AbstractClassyNode {

    List<T> children;

    boolean packageCheck = false;

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

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public abstract void addChild(AbstractClassyNode child);

    public abstract void removeChildren();

    @Override
    public String toString() {
        return super.name;
    }

    public boolean isPackageCheck() {
        return packageCheck;
    }

    public void setPackageCheck(boolean packageCheck) {
        this.packageCheck = packageCheck;
    }
}
