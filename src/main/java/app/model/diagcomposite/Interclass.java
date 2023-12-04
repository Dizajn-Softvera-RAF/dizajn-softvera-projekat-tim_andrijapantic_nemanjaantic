package app.model.diagcomposite;

import app.model.classcontent.*;
import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.DiagramElement;
import javafx.scene.effect.Light;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement {

    private Visibility visibility;

    protected Paint paint;

    protected String description;
    protected Point position;
    private List<ClassContent> content;

    public Interclass(Paint paint, Point position) {
        this.paint = paint;
        this.position = position;
        this.content = new ArrayList<>();
    }

    public Interclass(String name, AbstractClassyNode parent) {
        super(name, parent);
    }
    public Interclass(String name, AbstractClassyNode parent, Point position) {
        super(name, parent);
        this.position = position;
        this.content = new ArrayList<>();
    }

    public Interclass() {

    }

    public Interclass(Point position, String name) {
        super(name);
        this.position = position;
        this.content = new ArrayList<>();
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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public List<ClassContent> getContent() {
        return content;
    }

    public void setContent(List<ClassContent> content) {
        this.content = content;
    }
}
