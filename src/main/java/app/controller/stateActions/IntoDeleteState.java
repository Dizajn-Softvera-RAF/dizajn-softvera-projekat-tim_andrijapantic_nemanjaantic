package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoDeleteState extends AbstractClassyAction {
    public IntoDeleteState() {
        putValue(SMALL_ICON, loadIcon("/images/delete-icon.png"));
        putValue(NAME, "Delete State");
        putValue(SHORT_DESCRIPTION, "Enter Delete State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getDeleteState());
        System.out.println("Usao sam u delete state.");
    }
}
