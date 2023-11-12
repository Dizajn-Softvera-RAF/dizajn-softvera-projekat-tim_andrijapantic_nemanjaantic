package app.model.logger.event;

public interface ISubscriber {
    void update(Notification notification);
}
