package app.model.diagcomposite;

import app.model.classcontent.*;
import app.model.composite.AbstractClassyNode;
import app.model.diagcomposite.DiagramElement;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import javafx.scene.effect.Light;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement {

    protected Paint paint;

    protected String description;
    protected Point position;
    private List<ClassContent> content;
    ArrayList<Point> connectionsDots;

    public Interclass(Paint paint, Point position) {
        this.paint = paint;
        this.position = position;
        this.content = new ArrayList<>();
        connectionsDots = new ArrayList<>();
    }

    public Interclass(String name, AbstractClassyNode parent) {
        super(name, parent);
        connectionsDots = new ArrayList<>();
    }
    public Interclass(String name, AbstractClassyNode parent, Point position) {
        super(name, parent);
        this.position = position;
        this.content = new ArrayList<>();
        connectionsDots = new ArrayList<>();
    }

    public Interclass() {

    }

    public Interclass(Point position, String name) {
        super(name);
        this.position = position;
        this.content = new ArrayList<>();
        connectionsDots = new ArrayList<>();
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
        notifySubscribers(new Notification(NotificationType.PAINTER_POSITION_CHANGED));
    }

    public List<ClassContent> getContent() {
        return content;
    }

    public void setContent(List<ClassContent> content) {
        this.content = content;
    }

    public void addNewContent(ClassContent classContent) {
        getContent().add(classContent);
        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }

    public void deleteContent(int index) {
        getContent().remove(index);
        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }

    public void changeContent(int index, String name, String type, Visibility visibility) {
        if (getContent().get(index) instanceof EnumType) {
            getContent().get(index).setName(name);
        } else {
            getContent().get(index).setName(name);
            getContent().get(index).setType(type);
            getContent().get(index).setVisibility(visibility);
        }
        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }

    @Override
    public void setName(String name) {
        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
        super.setName(name);
    }

    public ArrayList<Point> getConnectionsDots() {
        return connectionsDots;
    }

    public void setConnectionsDots(ArrayList<Point> connectionsDots) {
        this.connectionsDots = connectionsDots;
    }
}
