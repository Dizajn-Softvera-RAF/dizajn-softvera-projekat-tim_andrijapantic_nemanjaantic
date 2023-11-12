package app.model.logger;

import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.message.Message;

public abstract class Logger implements ISubscriber {
    public void log(Message message) {
    }

    @Override
    public void update(Notification notification) {
    }
}
