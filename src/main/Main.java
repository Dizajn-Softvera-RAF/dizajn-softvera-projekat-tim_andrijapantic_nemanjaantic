package main;

import core.AppCore;
import core.SwingGui;
import core.SwingGuiJava;
import core.*;
import model.repository.ClassyRepImplementation;
import model.repository.ClassyRepository;

public class Main {

    public static void main(String[] args) {

        AppCore appCore = AppCore.getInstance();
        SwingGui gui = new SwingGuiJava();
        ClassyRepository classyRepository = new ClassyRepImplementation();
        appCore.initialise(gui, classyRepository);
        appCore.run();



    }
}
