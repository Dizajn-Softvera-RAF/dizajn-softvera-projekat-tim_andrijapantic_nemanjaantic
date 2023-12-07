package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoDuplicationState extends AbstractClassyAction {

    public IntoDuplicationState() {
        putValue(SMALL_ICON, loadIcon("/images/duplication.png"));
        putValue(NAME, "Duplication State");
        putValue(SHORT_DESCRIPTION, "Enter Duplication State");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getDuplicationState());
        System.out.println("Usao sam u duplication state.");
    }
}
