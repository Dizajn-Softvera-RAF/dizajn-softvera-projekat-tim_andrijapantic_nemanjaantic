package app.model.event;

import app.model.message.Message;
import app.model.tree.MyNodeMutable;

import java.util.UUID;

public class Notification {
    private Message message = null;
    private String title;
    private NotificationType type;
    private UUID id;
    private MyNodeMutable node;

    public Notification() {
    }

    public Notification(NotificationType type, String title) {
        this.type = type;
        this.title = title;
    }

    public Notification(NotificationType type, MyNodeMutable node) {
        this.type = type;
        this.node = node;
    }

    public Notification(NotificationType type, UUID id) {
        this.type = type;
        this.id = id;
    }

    public Notification(NotificationType type, UUID id, String title) {
        this.type = type;
        this.id = id;
        this.title = title;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MyNodeMutable getNode() {
        return node;
    }

    public void setNode(MyNodeMutable nodeToDelete) {
        this.node = nodeToDelete;
    }
}
