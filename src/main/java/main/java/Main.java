package main.java;

import model.core.AppCore;
import model.core.SwingGui;
import model.core.*;

public class Main {

    public static void main(String[] args) {

        AppCore appCore = AppCore.getInstance();
        SwingGui gui = new SwingGuiJava();
        appCore.run();



    }
}
