package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;
import app.model.diagimplementation.connection.editcomponents.Cardinality;

import java.awt.*;
import java.util.ArrayList;

public class Aggregation extends Connection {

    ArrayList<Cardinality> cardinalityList;

    public Aggregation() {
        cardinalityList = new ArrayList<>();
    }

    public Aggregation(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent, color, lineWidth);
        cardinalityList = new ArrayList<>();
    }

    public Aggregation(String name, ClassyNodeComposite parent) {
        super(name,parent);
        cardinalityList = new ArrayList<>();
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }

    public ArrayList<Cardinality> getCardinalitiesList() {
        return cardinalityList;
    }

    public void setCardinalitiesList(ArrayList<Cardinality> cardinalityList) {
        this.cardinalityList = cardinalityList;
    }
}
