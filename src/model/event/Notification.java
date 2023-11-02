package model.event;

import model.message.Message;

public class Notification {
    Message message;
    public Notification() {}

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
