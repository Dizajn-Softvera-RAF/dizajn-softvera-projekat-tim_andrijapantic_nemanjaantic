package controller;

import java.awt.event.ActionEvent;

public class NewProjectAction extends AbstractClassyAction{

    public NewProjectAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-project.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
