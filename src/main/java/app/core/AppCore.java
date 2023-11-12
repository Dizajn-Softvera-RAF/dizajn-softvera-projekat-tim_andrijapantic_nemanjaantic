package app.core;

import app.model.logger.Logger;
import app.model.logger.LoggerFactory;
import app.model.logger.LoggerType;
import app.model.repository.ClassyRepository;

public class AppCore {

    private static AppCore instance = null;
    protected SwingGui gui;
    private ClassyRepository classyRepository;
    private Logger consoleLogger;
    private Logger fileLogger;

    private AppCore() {

    }

    public static AppCore getInstance() {
        if (instance == null)
            instance = new AppCore();
        return instance;
    }

    public void initialise(SwingGui gui) {
        this.gui = gui;
    }

    public void initialise(SwingGui gui, ClassyRepository classyRepository) {
        this.gui = gui;
        this.classyRepository = classyRepository;
        consoleLogger =  LoggerFactory.createLogger(LoggerType.CONSOLE);
        fileLogger = LoggerFactory.createLogger(LoggerType.FILE);
    }

    public void run() {
        this.gui.start();
    }

    public SwingGui getGui() {
        return gui;
    }

    public void setGui(SwingGui gui) {
        this.gui = gui;
    }

    public ClassyRepository getClassyRepository() {
        return classyRepository;
    }

    public void setClassyRepository(ClassyRepository classyRepository) {
        this.classyRepository = classyRepository;
    }

    public Logger getConsoleLogger() {
        return consoleLogger;
    }

    public void setConsoleLogger(Logger consoleLogger) {
        this.consoleLogger = consoleLogger;
    }

    public Logger getFileLogger() {
        return fileLogger;
    }

    public void setFileLogger(Logger fileLogger) {
        this.fileLogger = fileLogger;
    }
}
