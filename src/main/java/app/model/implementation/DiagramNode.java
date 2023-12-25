package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DiagramElement.class, name = "DiagramElement"),
})
@JsonTypeName("DiagramNode")
public class DiagramNode extends ClassyNodeComposite<DiagramElement> implements IPublisher {
    @JsonIgnore
    private List<ISubscriber> subscribers;
    @JsonIgnore
    private MyNodeMutable myNodeMutable;
    private String path;
    private boolean changed = true;

    public DiagramNode() {
        this.subscribers = new ArrayList<>();
    }
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

    @Override
    public void removeChildren() {
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

    public void generateFile(String path) {
        File file = new File(path, getName() + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
        }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
