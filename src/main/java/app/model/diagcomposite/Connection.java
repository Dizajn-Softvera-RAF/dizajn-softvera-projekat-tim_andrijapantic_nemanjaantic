package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;

import java.awt.*;

public abstract class Connection extends DiagramElement {

    private Interclass fromInterclass;
    private Interclass toInterclass;
    private Color color;
    private Integer lineWidth;

    public Connection(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent);
        this.color = color;
        this.lineWidth = lineWidth;
    }

    public Connection () {

    }

    public Connection(String name, ClassyNodeComposite parent) {
        super(name,parent);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Integer lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Interclass getFromInterclass() {
        return fromInterclass;
    }

    public void setFromInterclass(Interclass fromInterclass) {
        this.fromInterclass = fromInterclass;
    }

    public Interclass getToInterclass() {
        return toInterclass;
    }

    public void setToInterclass(Interclass toInterclass) {
        this.toInterclass = toInterclass;
    }
}
