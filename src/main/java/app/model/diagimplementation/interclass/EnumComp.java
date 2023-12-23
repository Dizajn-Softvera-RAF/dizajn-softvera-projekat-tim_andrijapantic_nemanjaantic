package app.model.diagimplementation.interclass;

import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.Interclass;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.awt.*;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class EnumComp extends Interclass {
    private Dimension size;
    int stroke;
    public EnumComp(String name, AbstractClassyNode parent) {
        super(name,parent);
    }

    public EnumComp() {
        super();
    }

    public EnumComp(Point position, AbstractClassyNode parent, String name, int stroke) {
        super(name, parent, position);
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
