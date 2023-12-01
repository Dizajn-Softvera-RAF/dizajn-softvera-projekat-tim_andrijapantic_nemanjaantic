package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoAddInterclassState extends AbstractClassyAction {
    public IntoAddInterclassState() {
        putValue(SMALL_ICON, loadIcon("/images/interclass-icon.png"));
        putValue(NAME, "Interclass State");
        putValue(SHORT_DESCRIPTION, "Enter Interclass State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TabbedPane.getInstance().getPackageView().startState(TabbedPane.getInstance().getPackageView().getStateManager().getAddInterclassState());
        System.out.println("Usao sam u add interclass state.");
    }
}
