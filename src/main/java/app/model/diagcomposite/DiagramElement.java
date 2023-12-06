package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DiagramElement extends ClassyNodeComposite<Void> implements IPublisher {

    List<ISubscriber> subscribers;
    boolean isSelected = false;

    private Color currentColor;

    private Color initialColor;


    public DiagramElement() {
    }


    public DiagramElement(String name, AbstractClassyNode parent, List<Void> children) {
        super(name, parent, children);
        subscribers = new ArrayList<>();
    }

    public DiagramElement(String name, AbstractClassyNode parent) {
        super(name, parent);
        subscribers = new ArrayList<>();
    }


    public DiagramElement(String name) {
        super(name);
        subscribers = new ArrayList<>();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if(selected == true) {
            currentColor = Color.PINK;
        } else {
            currentColor = initialColor;
        }

        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Color getInitialColor() {
        return initialColor;
    }

    public void setInitialColor(Color initialColor) {
        this.initialColor = initialColor;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (ISubscriber subscriber : subscribers) {

            subscriber.update(notification);
        }
    }
}
