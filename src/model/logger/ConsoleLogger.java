package model.logger;

import model.event.Notification;
import model.message.Message;

public class ConsoleLogger extends Logger {
    @Override
    public void log(Message message) {
        System.out.println(message);
    }

    @Override
    public void update(Notification notification) {
        log(notification.getMessage());
    }
}
