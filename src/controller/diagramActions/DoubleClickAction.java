package controller.diagramActions;

import controller.AbstractClassyAction;
import model.event.Notification;
import model.event.NotificationType;
import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.mainframe.MainFrame;
import model.repository.composite.ClassyNodeComposite;
import model.repository.implementation.DiagramNode;
import model.repository.implementation.PackageNode;
import view.tabs.TabbedPane;
import view.tree.ClassyTreeView;

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
                                TabbedPane.getInstance().addNewPane(diagramNode.getName(), MainFrame.getInstance().getSelectedNode().getClassyNode().getId(), MainFrame.getInstance().getSelectedNode());
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
