package app.controller.diagramActions;

import app.controller.AbstractClassyAction;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.view.mainframe.MainFrame;
import app.model.composite.ClassyNodeComposite;
import app.model.implementation.DiagramNode;
import app.model.implementation.PackageNode;
import app.view.tabs.TabbedPane;
import app.view.tree.ClassyTreeView;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DoubleClickAction extends AbstractClassyAction implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof ClassyNodeComposite) {
                if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof DiagramNode) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        if (!TabbedPane.getInstance().isTabPresent(MainFrame.getInstance().getSelectedNode().getClassyNode().getName())) {
                            TabbedPane.getInstance().addNewPane(MainFrame.getInstance().getSelectedNode().getClassyNode().getName(), MainFrame.getInstance().getSelectedNode().getClassyNode().getId(), MainFrame.getInstance().getSelectedNode());
                        }
                    }
                } else if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof PackageNode) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        TabbedPane.getInstance().closeAllTabs();
                        ClassyTreeView projectExplorer = MainFrame.getInstance().getClassyTree().getTreeView();
                        projectExplorer.notifySubscribers(new Notification(NotificationType.PACKAGE_SELECTED, MainFrame.getInstance().getSelectedNode()));
                        PackageNode paket = (PackageNode) MainFrame.getInstance().getSelectedNode().getClassyNode();
                        for (DiagramNode diagramNode : paket.getChildren()) {
                            if (!TabbedPane.getInstance().isTabPresent(diagramNode.getName())) {
                                TabbedPane.getInstance().addNewPane(diagramNode.getName(), diagramNode.getId(), MainFrame.getInstance().getSelectedNode());
                            }
                        }
                    }
                }

            }
        } catch (NullPointerException exception) {

            Message message = new Message(PossibleErr.ONLY_USE_DOUBLE_CLICK_ON_NODES);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
