package app.controller.diagramActions;

import app.controller.AbstractClassyAction;
import app.view.tabs.Tab;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseTabAction extends AbstractClassyAction implements ActionListener {

    private Tab tab;

    public CloseTabAction(Tab tab) {
        this.tab = tab;
    }

    public Tab getTab() {
        return tab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        TabbedPane.getInstance().removeTab(tab.getTitle(), null);
    }
}
