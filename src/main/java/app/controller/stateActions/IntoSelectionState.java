package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoSelectionState extends AbstractClassyAction {
    public IntoSelectionState() {
        putValue(SMALL_ICON, loadIcon("/images/selection-icon.png"));
        putValue(NAME, "Selection State");
        putValue(SHORT_DESCRIPTION, "Enter Selection State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TabbedPane.getInstance().getPackageView().startState(TabbedPane.getInstance().getPackageView().getStateManager().getSelectionState());
        System.out.println("Usao sam u select state.");
    }
}
