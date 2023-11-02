package model.message;

import model.event.IPublisher;
import model.event.ISubscriber;
import model.event.Notification;
import model.logger.LoggerFactory;
import model.logger.LoggerType;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {

    public List<ISubscriber> subscribers;

    public MessageGenerator() {
        this.subscribers = new ArrayList<>();

        subscribers.add(LoggerFactory.createLogger(LoggerType.CONSOLE));
        subscribers.add(LoggerFactory.createLogger(LoggerType.FILE));
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
