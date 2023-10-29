package main.java;

import main.java.model.core.AppCore;

public class Main {

    public static void main(String[] args) {

        AppCore appCore = AppCore.getInstance();
        SwingGui gui = new SwingGuiJava();
        appCore.run();



    }
}
