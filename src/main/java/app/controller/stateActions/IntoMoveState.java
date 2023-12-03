package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoMoveState extends AbstractClassyAction {

    public IntoMoveState() {
        putValue(SMALL_ICON, loadIcon("/images/move-icon.png"));
        putValue(NAME, "Move State");
        putValue(SHORT_DESCRIPTION, "Enter Move State");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabbedPane.getInstance().getPackageView().startState(TabbedPane.getInstance().getPackageView().getStateManager().getMoveState());
        System.out.println("Usao sam u move state.");
    }
}
