package core;

import model.repository.ClassyRepository;

public class AppCore {

    private static AppCore instance = null;
    protected SwingGui gui;
    private ClassyRepository classyRepository;

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
}
