package core;


import view.mainframe.MainFrame;

import javax.swing.*;

public class SwingGuiJava implements SwingGui {

    private MainFrame instance;

    public SwingGuiJava() {

    }
    @Override
    public void start() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

            e.printStackTrace();
        }


        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }
}
