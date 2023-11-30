package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoAddConnectionState extends AbstractClassyAction {
    public IntoAddConnectionState() {
        putValue(SMALL_ICON, loadIcon("/images/connection-icon.png"));
        putValue(NAME, "Connection State");
        putValue(SHORT_DESCRIPTION, "Enter Connection State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TabbedPane.getInstance().getPackageView().startState(TabbedPane.getInstance().getPackageView().getStateManager().getAddConnectionState());
        System.out.println("Usao sam u add connection state.");
    }
}
