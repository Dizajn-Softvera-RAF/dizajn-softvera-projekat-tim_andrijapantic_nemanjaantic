package app.model.diagcomposite;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.tree.MyNodeMutable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Interclass.class, name = "Interclass"),
        @JsonSubTypes.Type(value = Connection.class, name = "Connection"),
})
public abstract class DiagramElement extends ClassyNodeComposite<Void> implements IPublisher {

    @JsonIgnore
    List<ISubscriber> subscribers;
    boolean isSelected = false;

    private int currentColor;

    private int initialColor;
    @JsonIgnore
    private MyNodeMutable myNodeMutable;


    public DiagramElement() {
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
            currentColor = 16761035; //roze boja za selected
        } else {
            currentColor = initialColor;
        }

        notifySubscribers(new Notification(NotificationType.PAINTER_STATE_CHANGED));
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int color) {
        this.currentColor = color;
    }

    public int getInitialColor() {
        return initialColor;
    }

    public void setInitialColor(int initialColor) {
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

    public MyNodeMutable getMyNodeMutable() {
        return myNodeMutable;
    }

    public void setMyNodeMutable(MyNodeMutable myNodeMutable) {
        this.myNodeMutable = myNodeMutable;
    }
}
