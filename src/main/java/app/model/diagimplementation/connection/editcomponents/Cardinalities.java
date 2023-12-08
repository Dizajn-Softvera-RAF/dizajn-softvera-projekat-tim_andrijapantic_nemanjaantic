package app.model.diagimplementation.connection.editcomponents;

import app.model.classcontent.Attribute;
import app.model.diagimplementation.connection.editcomponents.Cardinality;
import app.model.diagcomposite.Interclass;

public class Cardinalities {
    private Cardinality cardinality;
    private Interclass interclassThatContains;
    private Attribute attributeUsed;
    private Attribute newAttribute;

    public Cardinalities(Cardinality cardinality, Interclass interclassThatContains, Attribute attributeUsed, Attribute newAttribute) {
        this.cardinality = cardinality;
        this.interclassThatContains = interclassThatContains;
        this.attributeUsed = attributeUsed;
        this.newAttribute = newAttribute;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }

    public Interclass getInterclassThatContains() {
        return interclassThatContains;
    }

    public void setInterclassThatContains(Interclass interclassThatContains) {
        this.interclassThatContains = interclassThatContains;
    }

    public Attribute getAttributeUsed() {
        return attributeUsed;
    }

    public void setAttributeUsed(Attribute attributeUsed) {
        this.attributeUsed = attributeUsed;
    }

    public Attribute getNewAttribute() {
        return newAttribute;
    }

    public void setNewAttribute(Attribute newAttribute) {
        this.newAttribute = newAttribute;
    }

    @Override
    public String toString() {
        return "Interclass " + interclassThatContains.getName() + ", Attribute name: " + newAttribute.getName() + ", Cardinalty: " + cardinality.toString() + " of Attribute " + attributeUsed.getName();
    }
}
