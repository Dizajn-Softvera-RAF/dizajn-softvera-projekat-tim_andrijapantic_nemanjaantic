package app.model.diagimplementation.interclass;

import app.model.classcontent.ClassContent;
import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Interclass;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Klasa extends Interclass {


    private Dimension size;
    int stroke;
    //protected Point position;

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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }


}
