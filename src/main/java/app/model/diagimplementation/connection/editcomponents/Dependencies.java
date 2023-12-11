package app.model.diagimplementation.connection.editcomponents;

import app.model.classcontent.Attribute;
import app.model.classcontent.Method;
import app.model.diagcomposite.Interclass;

public class Dependencies {
    private Interclass thatUses;
    private Interclass  interclassDependedUpon;
    private DependencyType dependencyType;

    public Dependencies(Interclass thatUses, Interclass interclassDependedUpon, DependencyType dependencyType) {
        this.thatUses = thatUses;
        this.interclassDependedUpon = interclassDependedUpon;
        this.dependencyType = dependencyType;
    }

    public Interclass getThatUses() {
        return thatUses;
    }

    public void setThatUses(Interclass thatUses) {
        this.thatUses = thatUses;
    }

    public DependencyType getDependencyType() {
        return dependencyType;
    }

    public void setDependencyType(DependencyType dependencyType) {
        this.dependencyType = dependencyType;
    }

    @Override
    public String toString() {
        return "Interclass " + thatUses.getName() + " " + dependencyType.toString() + "S " + interclassDependedUpon.getName();
    }
}
