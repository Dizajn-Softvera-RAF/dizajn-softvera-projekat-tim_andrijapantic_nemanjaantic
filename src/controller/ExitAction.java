package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static javax.swing.Action.*;

public class ExitAction extends AbstractClassyAction{

    public ExitAction() {
        putValue(SMALL_ICON, loadIcon("/images/exit.png"));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");

    }
    public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
