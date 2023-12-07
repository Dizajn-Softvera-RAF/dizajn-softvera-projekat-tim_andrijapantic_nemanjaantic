package app.controller.stateActions;

import app.controller.AbstractClassyAction;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class IntoAddConnectionState extends AbstractClassyAction {
    public IntoAddConnectionState() {
        putValue(SMALL_ICON, loadIcon("/images/connection-icon.png"));
        putValue(NAME, "Connection State");
        putValue(SHORT_DESCRIPTION, "Enter Connection State");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] tipKonekcije = {"Aggregation", "Composition", "Dependency", "Equivalence"};
        int odg = JOptionPane.showOptionDialog(null, "Choose the Connection Type",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipKonekcije, tipKonekcije[0]);
        System.out.println("Izabrana je opcija "  + tipKonekcije[odg]);
        MainFrame.getInstance().setConnectionType(odg);
        MainFrame.getInstance().getPackageView().startState(MainFrame.getInstance().getPackageView().getStateManager().getAddConnectionState());
        System.out.println("Usao sam u add connection state.");
    }
}
