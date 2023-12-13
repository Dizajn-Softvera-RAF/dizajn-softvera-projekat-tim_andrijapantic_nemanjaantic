package app.model.diagimplementation.interclass;

import app.model.classcontent.ClassContent;
import app.model.classcontent.Method;
import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.Interclass;

import java.awt.*;
import java.util.List;

public class Interface extends Interclass {

    private Dimension size;
    int stroke;

    public Interface(String name, AbstractClassyNode parent) {
        super(name,parent);
    }

    public Interface() {
    }

    public Interface(Point position, AbstractClassyNode parent, String name, int stroke) {
        super(name,parent,position);
        this.stroke = stroke;
    }

    @Override
    public void addChild(AbstractClassyNode child) {

    }

    @Override
    public void removeChildren() {

    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }


}
