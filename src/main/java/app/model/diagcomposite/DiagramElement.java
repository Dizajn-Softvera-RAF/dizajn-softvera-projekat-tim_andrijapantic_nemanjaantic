package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DiagramElement extends ClassyNodeComposite<Void> implements IPublisher {

    List<ISubscriber> subscribers;
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
