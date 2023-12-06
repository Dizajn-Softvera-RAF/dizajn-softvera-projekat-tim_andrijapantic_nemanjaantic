package app.model.diagimplementation.interclass;

import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Interclass;
import app.model.event.ISubscriber;
import app.model.event.Notification;

import java.awt.*;

public class Klasa extends Interclass {


    private Dimension size;
    int stroke;

    public Klasa(Point position, AbstractClassyNode parent, String name, int stroke) {
        super(name,parent,position);
        this.stroke = stroke;
    }

    public Klasa(Dimension size, Point position) {
        this.size= size;
        this.position = position;
    }

    public Klasa(String name, AbstractClassyNode parent) {
        super(name, parent);
    }
    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }


}
