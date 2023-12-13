package app.controller.state.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;
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
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getMoveState());
        System.out.println("Usao sam u move state.");
    }
}
