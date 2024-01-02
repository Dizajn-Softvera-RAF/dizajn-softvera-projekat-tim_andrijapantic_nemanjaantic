package app.controller.treeActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.model.implementation.ProjectExplorer;

import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractClassyAction {
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
                AppCore.getInstance().showMessage(PossibleErr.CANNOT_DELETE_PROJECT_EXPLORER);
                
            }
            else {
                DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
                nodeToDelete.getClassyNode().removeChildren();
                try {
                    model.removeNodeFromParent(nodeToDelete);
                } catch (NullPointerException e1) {

                }

            }


        } catch (NullPointerException exception) {

            AppCore.getInstance().showMessage(PossibleErr.CANNOT_DELETE_ELEMENT);
            

        }
    }
}
