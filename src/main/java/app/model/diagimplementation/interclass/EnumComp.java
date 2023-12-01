package app.model.diagimplementation.interclass;

import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Interclass;

import java.awt.*;
import java.util.List;

public class EnumComp extends Interclass {

    private List<String> children;
    private Dimension size;
    int stroke;
    public EnumComp(String name, AbstractClassyNode parent) {
        super(name,parent);
    }

    public EnumComp() {}

    public EnumComp(int stroke, Point position, Paint paint, Dimension size) {
        super(paint, position);
        this.stroke = stroke;
        this.size = size;
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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
