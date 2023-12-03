package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class IntoZoomOutState extends AbstractClassyAction {

    public IntoZoomOutState() {
        putValue(SMALL_ICON, loadIcon("/images/zoom-out-icon.png"));
        putValue(NAME, "Zoom Out State");
        putValue(SHORT_DESCRIPTION, "Enter Zoom Out State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TabbedPane.getInstance().getPackageView().startState(TabbedPane.getInstance().getPackageView().getStateManager().getZoomOutState());
        System.out.println("Usao sam u zoom-out state.");
    }
}
