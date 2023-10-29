package model.core;

public class AppCore {

    protected SwingGui gui;


    private static AppCore instance = null;

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
    public void run() {
        this.gui.start();
    }

    public SwingGui getGui() {
        return gui;
    }

    public void setGui(SwingGui gui) {
        this.gui = gui;
    }
}
