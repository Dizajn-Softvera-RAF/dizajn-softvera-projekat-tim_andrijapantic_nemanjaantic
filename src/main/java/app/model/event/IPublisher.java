package app.model.event;

public interface IPublisher {
    void addSubscriber(ISubscriber sub);

    void removeSubscriber(ISubscriber sub);

    void notifySubscribers(Notification notification);
}
