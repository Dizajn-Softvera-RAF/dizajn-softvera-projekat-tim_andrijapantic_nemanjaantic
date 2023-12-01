package app.model.diagimplementation.interclass;

import app.model.classcontent.ClassContent;
import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Interclass;

import java.awt.*;
import java.util.List;

public class Klasa extends Interclass {

    private List<ClassContent> children;
    private Dimension size;
    int stroke;
    protected Point position;

    public Klasa() {
    }

    public Klasa(String name, AbstractClassyNode parent) {
        super(name, parent);
    }
    public Klasa(int stroke, Point position, Paint paint, Dimension size) {
        super(paint);
        this.stroke = stroke;
        this.size = size;
        this.position = position;
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
