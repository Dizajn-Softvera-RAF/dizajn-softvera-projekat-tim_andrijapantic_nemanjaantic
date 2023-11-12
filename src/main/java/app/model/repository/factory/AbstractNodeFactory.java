package app.model.repository.factory;

import app.model.repository.composite.ClassyNodeComposite;

public abstract class AbstractNodeFactory {
    public abstract ClassyNodeComposite create(String type, String name, ClassyNodeComposite parent) throws IllegalArgumentException;
}
