package app.controller.treeActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.implementation.ProjectNode;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.model.tree.MyNodeMutable;
import app.view.popups.AddToPackageView;
import app.view.mainframe.MainFrame;
import app.model.implementation.PackageNode;

import java.awt.event.ActionEvent;

public class NewTreeChildAction extends AbstractClassyAction {

    public NewTreeChildAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-project.png"));
        putValue(NAME, "Plus Action");
        putValue(SHORT_DESCRIPTION, "New Tree Child Action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MyNodeMutable selected = MainFrame.getInstance().getSelectedNode();
            if (selected.getClassyNode() instanceof PackageNode) {
                new AddToPackageView();
            } else {
                MyNodeMutable createdChild = MainFrame.getInstance().getClassyTree().addChild(selected, null);
                if (selected.getClassyNode() instanceof ProjectNode)
                    ((ProjectNode) selected.getClassyNode()).setChanged(true);
            }
        } catch (NullPointerException exception) {
            AppCore.getInstance().showMessage(PossibleErr.PROJECT_EXPLORER_MUST_BE_SELECTED_TO_CREATE_A_PROJECT);
        }
    }
}
