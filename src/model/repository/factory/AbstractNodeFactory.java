package model.repository.factory;

import model.repository.composite.ClassyNodeComposite;

public abstract class AbstractNodeFactory {
    public abstract ClassyNodeComposite create(String type, String name, ClassyNodeComposite parent) throws IllegalArgumentException;
}
