package controller;

import java.awt.event.ActionEvent;

public class NewComponentAction extends AbstractClassyAction{

    public NewComponentAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-component.png"));
        putValue(NAME, "New Component");
        putValue(SHORT_DESCRIPTION, "New Component");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
