package app.model.message;

import app.model.logger.event.IPublisher;
import app.model.logger.event.ISubscriber;
import app.model.logger.event.Notification;
import app.model.logger.LoggerFactory;
import app.model.logger.LoggerType;
import app.view.mainframe.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {

    public List<ISubscriber> subscribers;

    public MessageGenerator() {
        this.subscribers = new ArrayList<>();

        subscribers.add(LoggerFactory.createLogger(LoggerType.CONSOLE));
        subscribers.add(LoggerFactory.createLogger(LoggerType.FILE));
        subscribers.add(MainFrame.getInstance());
    }

    public void generateMsg(Message message) {

        Notification notification = new Notification();
        notification.setMessage(message);
        this.notifySubscribers(notification);

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
        for (ISubscriber scb : subscribers) {

            scb.update(notification);
        }
    }
}
