package view.repository.composite;

public abstract class AbstractClassyNode {

    protected String name;
    protected transient AbstractClassyNode parent;

    public AbstractClassyNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractClassyNode getParent() {
        return parent;
    }

    public void setParent(AbstractClassyNode parent) {
        this.parent = parent;
    }

    public AbstractClassyNode(String name, AbstractClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }
}
