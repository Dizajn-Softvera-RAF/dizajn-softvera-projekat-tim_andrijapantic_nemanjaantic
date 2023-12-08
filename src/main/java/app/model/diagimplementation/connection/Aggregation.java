package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;
import app.model.diagimplementation.connection.editcomponents.Cardinalities;

import java.awt.*;
import java.util.ArrayList;

public class Aggregation extends Connection {

    ArrayList<Cardinalities> cardinalitiesList;

    public Aggregation() {
        cardinalitiesList = new ArrayList<>();
    }

    public Aggregation(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent, color, lineWidth);
        cardinalitiesList = new ArrayList<>();
    }

    public Aggregation(String name, ClassyNodeComposite parent) {
        super(name,parent);
        cardinalitiesList = new ArrayList<>();
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }

    public ArrayList<Cardinalities> getCardinalitiesList() {
        return cardinalitiesList;
    }

    public void setCardinalitiesList(ArrayList<Cardinalities> cardinalitiesList) {
        this.cardinalitiesList = cardinalitiesList;
    }
}
