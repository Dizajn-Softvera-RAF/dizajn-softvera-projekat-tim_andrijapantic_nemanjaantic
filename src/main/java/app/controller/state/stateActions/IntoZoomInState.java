package app.controller.state.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoZoomInState extends AbstractClassyAction {

    public IntoZoomInState() {
        putValue(SMALL_ICON, loadIcon("/images/zoom-in-icon.png"));
        putValue(NAME, "Zoom In State");
        putValue(SHORT_DESCRIPTION, "Enter Zoom In State");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getZoomInState());
        System.out.println("Usao sam u zoom-in state.");
    }
}
