package app.model.logger;

import app.model.logger.event.ISubscriber;
import app.model.logger.event.Notification;
import app.model.message.Message;

public abstract class Logger implements ISubscriber {
    public void log(Message message) {
    }

    @Override
    public void update(Notification notification) {
    }
}
