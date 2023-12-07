package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;

import java.util.List;

import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
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
    private MyNodeMutable myNodeMutable;
    public DiagramNode(String name, AbstractClassyNode parent) {
        super(name, parent);
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof Klasa) {
            Klasa node = (Klasa) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Interface) {
            Interface node = (Interface) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof EnumComp) {
            EnumComp node = (EnumComp) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Aggregation) {
            Aggregation node = (Aggregation) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Composition) {
            Composition node = (Composition) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Generalization) {
            Generalization node = (Generalization) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Dependency) {
            Dependency node = (Dependency) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        }

    }

    public static void prodjiKrozDecu(MyNodeMutable node) {
        int brojDece = node.getChildCount();
        for (int i = 0; i < brojDece; i++) {
            MyNodeMutable childNode = (MyNodeMutable) node.getChildAt(i);
            //MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, childNode.getClassyNode().getId()));
            //prodjiKrozDecu(childNode);
        }
    }

    @Override
    public void removeChildren() {
     //   prodjiKrozDecu(MainFrame.getInstance().getSelectedNode());

        this.getChildren().clear();
        MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, this.getId()));
    }

    public void removeChildren(ArrayList<DiagramElement> lista) {
        for (DiagramElement element: lista) {
            if (this.getChildren().contains(element)) {
                this.getChildren().remove(element);
                notifySubscribers(new Notification(NotificationType.PAINTER_REMOVED));
            }
        }
    }

    public void tabOpened() {
        notifySubscribers(new Notification(NotificationType.EXISTING_TAB_OPENED));
    }

    public void removeChild(DiagramElement child) {
        if (this.getChildren().contains(child)) {
            this.getChildren().remove(child);
            notifySubscribers(new Notification(NotificationType.PAINTER_REMOVED));
        }
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

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public MyNodeMutable getMyNodeMutable() {
        return myNodeMutable;
    }

    public void setMyNodeMutable(MyNodeMutable myNodeMutable) {
        this.myNodeMutable = myNodeMutable;
    }
}
