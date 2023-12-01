package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.DiagramElement;

import javax.swing.text.Position;
import java.awt.*;

public abstract class Interclass extends DiagramElement {

    private String name;
    private Visibility visibility;

    protected Paint paint;

    protected String description;

    public Interclass(Paint paint) {
        this.paint = paint;
    }

    public Interclass(String name, AbstractClassyNode parent) {
        super(name, parent);
    }

    public Interclass() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

}
