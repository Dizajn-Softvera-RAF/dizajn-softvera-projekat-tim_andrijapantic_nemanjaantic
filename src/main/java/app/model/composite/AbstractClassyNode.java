package app.model.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public abstract class AbstractClassyNode {

    protected String name;
    @JsonIgnore
    transient protected AbstractClassyNode parent;
    protected UUID id;

    public AbstractClassyNode() {
    }

    public AbstractClassyNode(String name, AbstractClassyNode parent) {
        this.name = name;
        this.parent = parent;
        this.id = UUID.randomUUID();
    }

    public AbstractClassyNode(String name) {
        this.name = name;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
