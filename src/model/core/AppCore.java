package model.core;

import view.repository.ClassyRepository;

public class AppCore {

    protected SwingGui gui;

    private ClassyRepository classyRepository;


    private static AppCore instance = null;

    private AppCore() {

    }

    public static AppCore getInstance() {
        if (instance == null)
            instance = new AppCore();
        return instance;
    }

    public void initialise(model.core.SwingGui gui) {
        this.gui = gui;
    }

    public void initialise(SwingGui gui, ClassyRepository classyRepository) {
        this.gui = gui;
        this.classyRepository = classyRepository;
    }
    public void run() {
        this.gui.start();
    }

    public model.core.SwingGui getGui() {
        return gui;
    }

    public void setGui(model.core.SwingGui gui) {
        this.gui = gui;
    }

    public ClassyRepository getClassyRepository() {
        return classyRepository;
    }

    public void setClassyRepository(ClassyRepository classyRepository) {
        this.classyRepository = classyRepository;
    }
}
