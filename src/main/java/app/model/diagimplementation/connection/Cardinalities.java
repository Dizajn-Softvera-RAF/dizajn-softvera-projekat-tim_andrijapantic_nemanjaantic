package app.model.diagimplementation.connection;

import app.model.classcontent.Attribute;
import app.model.classcontent.Cardinality;
import app.model.diagcomposite.Interclass;

public class Cardinalities {
    private Cardinality cardinality;
    private Interclass interclassThatContains;
    private Attribute attributeUsed;

    public Cardinalities(Cardinality cardinality, Interclass interclassThatContains, Attribute attributeUsed) {
        this.cardinality = cardinality;
        this.interclassThatContains = interclassThatContains;
        this.attributeUsed = attributeUsed;
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
}
