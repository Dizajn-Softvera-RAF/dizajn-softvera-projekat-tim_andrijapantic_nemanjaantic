package app.controller.state.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;
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
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getEditState());
        System.out.println("Usao sam u edit state.");
    }
}
