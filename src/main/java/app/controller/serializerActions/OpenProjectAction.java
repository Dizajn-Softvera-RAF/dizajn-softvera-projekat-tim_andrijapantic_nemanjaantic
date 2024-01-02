package app.controller.serializerActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.implementation.ProjectNode;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenProjectAction extends AbstractClassyAction {
    public OpenProjectAction() {
        putValue(SMALL_ICON, loadIcon("/images/open.png"));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(NAME, "Open action");
        putValue(SHORT_DESCRIPTION, "Open action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        // Ucitani projekat se ne vidi odmah u stablu ako Project Explorer nije bio prethodno selektovan
        // kada se doda novi projekat itd prikazace se
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                ProjectNode p = AppCore.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getClassyTree().loadProject(p);
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
