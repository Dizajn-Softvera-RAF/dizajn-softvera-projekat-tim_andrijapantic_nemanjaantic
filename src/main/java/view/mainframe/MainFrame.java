package main.java.view.mainframe;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;


    public static MainFrame getInstance() {

        if (instance == null) {
            instance = new MainFrame();
            instance.instilize();

        }
        return instance;
    }

    private void instilize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize = kit.getScreenSize();
        int screenHeight = screensize.height;
        int screenWidth = screensize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Projekat");

        setLayout(new BorderLayout());

    }



}
