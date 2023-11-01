package controller;

import java.awt.event.ActionEvent;

public class NewPackageAction extends AbstractClassyAction{

    public NewPackageAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-package.png"));
        putValue(NAME, "New Package");
        putValue(SHORT_DESCRIPTION, "New Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
