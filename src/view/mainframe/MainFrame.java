package view.mainframe;

import controller.ActionManager;
import model.event.ISubscriber;
import model.event.Notification;
import view.dialogs.MessagePane;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance = null;

    private static ToolBar toolBar;

    private ActionManager actionManager;
    private Menu menu;

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

        actionManager = new ActionManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize = kit.getScreenSize();
        int screenHeight = screensize.height;
        int screenWidth = screensize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        menu = new Menu();
        setJMenuBar(menu);


        toolBar = new ToolBar();
        toolBar.setSize(100, 50);
        add(toolBar, BorderLayout.NORTH);



    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public static ToolBar getToolBar() {
        return toolBar;
    }

    public static void setToolBar(ToolBar toolBar) {
        MainFrame.toolBar = toolBar;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void update(Notification notification) {
        new MessagePane(notification.getMessage());
    }
}
