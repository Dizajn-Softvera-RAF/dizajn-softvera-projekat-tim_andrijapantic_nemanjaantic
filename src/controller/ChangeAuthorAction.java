package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractClassyAction{
    public ChangeAuthorAction() {
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog("Unesi ime autora ");
    }
}
