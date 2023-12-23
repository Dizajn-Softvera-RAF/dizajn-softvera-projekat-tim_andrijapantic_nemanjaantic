package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.model.event.Notification;
import app.model.event.NotificationType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Aggregation.class, name = "Aggregation"),
        @JsonSubTypes.Type(value = Composition.class, name = "Composition"),
        @JsonSubTypes.Type(value = Dependency.class, name = "Dependency"),
        @JsonSubTypes.Type(value = Generalization.class, name = "Generalization")
})
public abstract class Connection extends DiagramElement {

    private Interclass fromInterclass;
    private Interclass toInterclass;
    private Color color;
    private Integer lineWidth;

    private Point startPoint;
    private  Point endPoint;

    public Connection(String name, AbstractClassyNode parent, Color color, Integer lineWidth) {
        super(name, parent);
        this.color = color;
        this.lineWidth = lineWidth;
    }

    public Connection () {
        super();
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

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }
}
