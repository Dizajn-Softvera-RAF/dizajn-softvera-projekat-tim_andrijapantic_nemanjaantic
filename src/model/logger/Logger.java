package model.logger;

import model.event.ISubscriber;
import model.event.Notification;
import model.message.Message;

public abstract class Logger implements ISubscriber {
    public void log(Message message) {}
    @Override
    public void update(Notification notification) {}
}
