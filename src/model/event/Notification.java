package model.event;

import model.message.Message;
import model.tree.MyNodeMutable;

import java.util.UUID;

public class Notification {
    private Message message;
    private String title;
    private NotificationType type;
    private UUID id;
    private MyNodeMutable nodeToDelete;
    public Notification() {}

    public Notification(NotificationType type, String title) {
        this.type = type;
        this.title = title;
    }
    /**
    public Notification(NotificationType type, MyNodeMutable projectToDelete) {
        this.type = type;
        this.nodeToDelete = projectToDelete;
    }
    **/
    public Notification(NotificationType type, UUID id) {
        this.type = type;
        this.id = id;
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

    public MyNodeMutable getNodeToDelete() {
        return nodeToDelete;
    }

    public void setNodeToDelete(MyNodeMutable nodeToDelete) {
        this.nodeToDelete = nodeToDelete;
    }
}
