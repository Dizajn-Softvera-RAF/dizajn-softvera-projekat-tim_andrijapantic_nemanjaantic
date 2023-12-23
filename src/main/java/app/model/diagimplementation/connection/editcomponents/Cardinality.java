package app.model.diagimplementation.connection.editcomponents;

import app.model.classcontent.Attribute;
import app.model.diagcomposite.Interclass;

public class Cardinality {
    private CardinalityType cardinalityType;
    private Interclass objectContained;
    private Interclass interclassThatContains;
    private Attribute newAttribute;

    public Cardinality(CardinalityType cardinalityType, Interclass interclassThatContains, Interclass objectContained, Attribute newAttribute) {
        this.cardinalityType = cardinalityType;
        this.interclassThatContains = interclassThatContains;
        this.objectContained = objectContained;
        this.newAttribute = newAttribute;
    }

    public CardinalityType getCardinality() {
        return cardinalityType;
    }

    public void setCardinality(CardinalityType cardinalityType) {
        this.cardinalityType = cardinalityType;
    }

    public Interclass getObjectContained() {
        return objectContained;
    }

    public void setObjectContained(Interclass objectContained) {
        this.objectContained = objectContained;
    }

    public Attribute getNewAttribute() {
        return newAttribute;
    }

    public void setNewAttribute(Attribute newAttribute) {
        this.newAttribute = newAttribute;
    }

    public Interclass getInterclassThatContains() {
        return interclassThatContains;
    }

    public void setInterclassThatContains(Interclass interclassThatContains) {
        this.interclassThatContains = interclassThatContains;
    }

    @Override
    public String toString() {
        return "Interclass " + interclassThatContains.getName() + " contains " +  cardinalityType.toString() + " of " + objectContained.getName() + " in attribute: " + newAttribute.attributeString();
    }
}
