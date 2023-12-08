package app.model.diagimplementation.connection.editcomponents;

import app.model.classcontent.Attribute;
import app.model.classcontent.Method;
import app.model.diagcomposite.Interclass;

public class Dependencies {
    private Interclass thatUses;
    private Method methodUsed = null;
    private Attribute attributeUsed = null;
    private DependencyType dependencyType;

    public Dependencies(Interclass thatUses, Method methodUsed, DependencyType dependencyType) {
        this.thatUses = thatUses;
        this.methodUsed = methodUsed;
        this.dependencyType = dependencyType;
    }

    public Dependencies(Interclass thatUses, Attribute attributeUsed, DependencyType dependencyType) {
        this.thatUses = thatUses;
        this.attributeUsed = attributeUsed;
        this.dependencyType = dependencyType;
    }

    public Interclass getThatUses() {
        return thatUses;
    }

    public void setThatUses(Interclass thatUses) {
        this.thatUses = thatUses;
    }

    public Method getMethodUsed() {
        return methodUsed;
    }

    public void setMethodUsed(Method methodUsed) {
        this.methodUsed = methodUsed;
    }

    public Attribute getAttributeUsed() {
        return attributeUsed;
    }

    public void setAttributeUsed(Attribute attributeUsed) {
        this.attributeUsed = attributeUsed;
    }

    public DependencyType getDependencyType() {
        return dependencyType;
    }

    public void setDependencyType(DependencyType dependencyType) {
        this.dependencyType = dependencyType;
    }

    @Override
    public String toString() {
        if  (methodUsed != null)
            return "Interclass: " + thatUses.getName() + " " + dependencyType.toString() + "S " + methodUsed.getMethodString();
        else
            return "Interclass: " + thatUses.getName() + " " + dependencyType.toString() + "S " + attributeUsed.getAttributeString();
    }
}
