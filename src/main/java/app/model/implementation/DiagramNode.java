package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;

import java.util.ArrayList;
import java.util.List;

public class DiagramNode extends ClassyNodeComposite<ElementNode> implements IPublisher {
    private List<ISubscriber> subscribers;

    public DiagramNode(String name, AbstractClassyNode parent) {
        super(name, parent);
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof ElementNode) {
            ElementNode node = (ElementNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }

    }

    @Override
    public void removeChildren() {

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

    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
