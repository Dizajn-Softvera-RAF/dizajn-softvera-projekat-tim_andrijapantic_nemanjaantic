package app;

import app.core.AppCore;
import app.core.SwingGui;
import app.core.SwingGuiJava;
import app.model.repository.ClassyRepImplementation;
import app.model.repository.ClassyRepository;

public class Main {

    public static void main(String[] args) {

        AppCore appCore = AppCore.getInstance();
        SwingGui gui = new SwingGuiJava();
        ClassyRepository classyRepository = new ClassyRepImplementation();
        appCore.initialise(gui, classyRepository);
        appCore.run();



    }
}
