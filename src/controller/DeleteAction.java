package controller;

import model.event.Notification;
import model.event.NotificationType;
import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import model.tree.MyNodeMutable;
import view.mainframe.MainFrame;
import model.repository.implementation.DiagramNode;
import model.repository.implementation.PackageNode;
import model.repository.implementation.ProjectExplorer;
import model.repository.implementation.ProjectNode;
import view.tabs.Tab;
import view.tabs.TabbedPane;
import view.tree.ClassyTreeView;

import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.util.ConcurrentModificationException;
import java.util.UUID;

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
                nodeToDelete.getClassyNode().removeChildren();
                model.removeNodeFromParent(nodeToDelete);

            }


        } catch (NullPointerException exception) {
            exception.printStackTrace();

            Message message = new Message(PossibleErr.CANNOT_DELETE_ELEMENT);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);

        }
    }
}
