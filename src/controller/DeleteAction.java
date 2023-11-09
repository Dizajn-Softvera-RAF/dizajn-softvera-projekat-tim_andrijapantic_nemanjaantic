package controller;

import model.event.Notification;
import model.event.NotificationType;
import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import model.tree.MyNodeMutable;
import view.dialogs.MessagePane;
import view.mainframe.MainFrame;
import view.repository.composite.AbstractClassyNode;
import view.repository.composite.ClassyNodeComposite;
import view.repository.implementation.DiagramNode;
import view.repository.implementation.PackageNode;
import view.repository.implementation.ProjectExplorer;
import view.repository.implementation.ProjectNode;
import view.tabs.Tab;
import view.tabs.TabbedPane;
import view.tree.ClassyTreeView;

import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.UUID;

import static javafx.scene.input.KeyCode.T;

public class DeleteAction extends AbstractClassyAction{
    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MyNodeMutable nodeToDelete = MainFrame.getInstance().getSelectedNode();
            if (nodeToDelete.getClassyNode() instanceof ProjectExplorer) {
                Message message = new Message(PossibleErr.CANNOT_DELETE_PROJECT_EXPLORER);
                MessageGenerator msggenerator = new MessageGenerator();
                msggenerator.generateMsg(message);
            }
            else {
                DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
                model.removeNodeFromParent(nodeToDelete);
                ClassyTreeView projectExplorer = MainFrame.getInstance().getClassyTree().getTreeView();
                System.out.println(nodeToDelete);
                UUID id = nodeToDelete.getClassyNode().getId();

                if (nodeToDelete.getClassyNode() instanceof DiagramNode) {
                    try {
                        Notification notification = new Notification(NotificationType.DELETE_DIAGRAM, nodeToDelete.getClassyNode().getId());
                        projectExplorer.notifySubscribers(notification);
                    } catch (ConcurrentModificationException exception) {}
                } else if (nodeToDelete.getClassyNode() instanceof PackageNode) {
                    Notification notification = new Notification(NotificationType.DELETE_PACKAGE, nodeToDelete);
                    TabbedPane.getInstance().getTrenutniTaboviZaBrisanje().clear();
                    projectExplorer.notifySubscribers(notification);
                    for (Tab tab: TabbedPane.getInstance().getTrenutniTaboviZaBrisanje()) {
                        int indexTaba = TabbedPane.getInstance().getIndexOfTab(tab.getTitle(), tab.getId());
                        TabbedPane.getInstance().removeTab(tab.getTitle(), tab.getId());
                        MainFrame.getInstance().getClassyTree().getTreeView().removeSubscriber(TabbedPane.getInstance().getListaTabova().get(indexTaba));
                        TabbedPane.getInstance().getListaTabova().remove(indexTaba);
                    }
                }
                else if (nodeToDelete.getClassyNode() instanceof ProjectNode) {
                    Notification notification = new Notification(NotificationType.DELETE_PROJECT, nodeToDelete);
                    projectExplorer.notifySubscribers(notification);
                    int n = nodeToDelete.getChildCount();
                    System.out.println("Broj dece koje brisem mog projekta: " + n);
                    TabbedPane.getInstance().getTrenutniTaboviZaBrisanje().clear();
                    for (int index=0; index<n; index++) {
                        if (nodeToDelete.getClassyNode().getChildren().get(index) instanceof PackageNode) {
                            projectExplorer.notifySubscribers(new Notification(NotificationType.DELETE_PACKAGE, (MyNodeMutable) nodeToDelete.getChildAt(index)));
                        }
                    }
                    for (Tab tab: TabbedPane.getInstance().getTrenutniTaboviZaBrisanje()) {
                        int indexTaba = TabbedPane.getInstance().getIndexOfTab(tab.getTitle(), tab.getId());
                        TabbedPane.getInstance().removeTab(tab.getTitle(), tab.getId());
                        MainFrame.getInstance().getClassyTree().getTreeView().removeSubscriber(TabbedPane.getInstance().getListaTabova().get(indexTaba));
                        TabbedPane.getInstance().getListaTabova().remove(indexTaba);
                    }
                }
            }


        } catch (NullPointerException exception) {
            exception.printStackTrace();

            Message message = new Message(PossibleErr.CANNOT_DELETE_ELEMENT);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);

        }
    }
}
