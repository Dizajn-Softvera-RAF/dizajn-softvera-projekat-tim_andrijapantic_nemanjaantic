package app.model.factory;

import app.model.composite.ClassyNodeComposite;

public abstract class AbstractNodeFactory {
    public abstract ClassyNodeComposite create(String type, String name, ClassyNodeComposite parent) throws IllegalArgumentException;
}
