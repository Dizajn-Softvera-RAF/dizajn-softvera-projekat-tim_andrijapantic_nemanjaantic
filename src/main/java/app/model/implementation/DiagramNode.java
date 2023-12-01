package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;

import java.util.List;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;
import java.util.ArrayList;
import java.util.List;




public class DiagramNode extends ClassyNodeComposite<DiagramElement> implements IPublisher {
    private List<ISubscriber> subscribers;
    private List<DiagramElement> children;
    public DiagramNode(String name, AbstractClassyNode parent) {
        super(name, parent);
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof DiagramElement) {
            DiagramElement node = (DiagramElement) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }

    }

    @Override
    public void removeChildren() {
        MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, this.getId()));

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

    @Override
    public List<DiagramElement> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<DiagramElement> children) {
        this.children = children;
    }
}
