package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoEditState extends AbstractClassyAction {
    public IntoEditState() {
        putValue(SMALL_ICON, loadIcon("/images/edit-icon.png"));
        putValue(NAME, "Edit State");
        putValue(SHORT_DESCRIPTION, "Enter Edit State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TabbedPane.getInstance().getPackageView().startState(TabbedPane.getInstance().getPackageView().getStateManager().getEditState());
        System.out.println("Usao sam u edit state.");
    }
}
