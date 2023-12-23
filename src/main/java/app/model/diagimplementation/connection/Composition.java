package app.model.diagimplementation.connection;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Connection;
import app.model.diagimplementation.connection.editcomponents.Cardinality;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Composition extends Connection {

    ArrayList<Cardinality> cardinalityList;

    public Composition(String name, ClassyNodeComposite parent) {
        super(name,parent);
        cardinalityList = new ArrayList<>();
    }

    public Composition() {}

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
