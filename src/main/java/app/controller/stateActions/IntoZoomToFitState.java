package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class IntoZoomToFitState extends AbstractClassyAction {

    public IntoZoomToFitState() {
        putValue(SMALL_ICON, loadIcon("/images/zoom-to-fit-icon.png"));
        putValue(NAME, "Zoom To Fit State");
        putValue(SHORT_DESCRIPTION, "Enter Zoom To Fit State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getZoomToFitState());
        System.out.println("Usao sam u zoom-to-fit state.");
    }
}
