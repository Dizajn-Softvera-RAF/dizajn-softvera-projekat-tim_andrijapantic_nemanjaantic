package controller;

import java.awt.event.ActionEvent;

public class InfoAction extends AbstractClassyAction {

    public InfoAction() {

        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
