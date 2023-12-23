package app.controller.diagramActions;

import app.controller.AbstractClassyAction;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.model.composite.ClassyNodeComposite;
import app.model.implementation.DiagramNode;
import app.model.implementation.PackageNode;
import app.view.mainframe.PackageView;
import app.view.tabs.Tab;
import app.view.tabs.TabbedPane;
import app.view.tree.ClassyTreeView;

import javax.swing.*;
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
                            Tab tab  = TabbedPane.getInstance().findTab(MainFrame.getInstance().getSelectedNode().getClassyNode().getName(), MainFrame.getInstance().getSelectedNode().getClassyNode().getId());
                            if (tab!=null) {
                                TabbedPane.getInstance().addTab(TabbedPane.getInstance().findTab(MainFrame.getInstance().getSelectedNode().getClassyNode().getName(), MainFrame.getInstance().getSelectedNode().getClassyNode().getId()), (DiagramNode)MainFrame.getInstance().getSelectedNode().getClassyNode(), MainFrame.getInstance().getSelectedNode());
                            } else
                                TabbedPane.getInstance().addNewPane(MainFrame.getInstance().getSelectedNode().getClassyNode().getName(), MainFrame.getInstance().getSelectedNode().getClassyNode().getId(), (DiagramNode) MainFrame.getInstance().getSelectedNode().getClassyNode(), MainFrame.getInstance().getSelectedNode());
                            ((DiagramNode) MainFrame.getInstance().getSelectedNode().getClassyNode()).tabOpened();
                        }
                    }
                } else if (MainFrame.getInstance().getSelectedNode().getClassyNode() instanceof PackageNode) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                        TabbedPane.getInstance().closeAllTabs();
                        ClassyTreeView projectExplorer = MainFrame.getInstance().getClassyTree().getTreeView();
                        projectExplorer.notifySubscribers(new Notification(NotificationType.PACKAGE_SELECTED, MainFrame.getInstance().getSelectedNode()));
                        PackageNode paket = (PackageNode) MainFrame.getInstance().getSelectedNode().getClassyNode();

                        for (ClassyNodeComposite diagramNode : paket.getChildren()) {
                            if (diagramNode instanceof DiagramNode) {
                                if (!TabbedPane.getInstance().isTabPresent(diagramNode.getName())) {
                                    Tab tab  = TabbedPane.getInstance().findTab(MainFrame.getInstance().getSelectedNode().getClassyNode().getName(), MainFrame.getInstance().getSelectedNode().getClassyNode().getId());
                                    if (tab!=null) {
                                        TabbedPane.getInstance().addTab(TabbedPane.getInstance().findTab(diagramNode.getName(), diagramNode.getId()), ((DiagramNode)diagramNode), ((DiagramNode)diagramNode).getMyNodeMutable());
                                    } else
                                        TabbedPane.getInstance().addNewPane(diagramNode.getName(), diagramNode.getId(), ((DiagramNode)diagramNode), ((DiagramNode)diagramNode).getMyNodeMutable());
                                    ((DiagramNode)diagramNode).tabOpened();
                                }
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
