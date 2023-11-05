package main;

import model.core.AppCore;
import model.core.SwingGui;
import model.core.*;
import view.repository.ClassyRepImplementation;
import view.repository.ClassyRepository;

public class Main {

    public static void main(String[] args) {

        AppCore appCore = AppCore.getInstance();
        SwingGui gui = new SwingGuiJava();
        ClassyRepository classyRepository = new ClassyRepImplementation();
        appCore.initialise(gui, classyRepository);
        appCore.run();



    }
}
