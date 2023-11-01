package controller;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractClassyAction{
    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
