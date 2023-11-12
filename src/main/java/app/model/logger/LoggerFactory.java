package app.model.logger;

public class LoggerFactory {
    public static Logger createLogger(LoggerType type) {
        if (type.equals(LoggerType.CONSOLE)) {
            return new ConsoleLogger();
        } else {
            return new FileLogger();
        }
    }
}
