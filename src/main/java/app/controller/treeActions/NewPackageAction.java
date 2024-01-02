package app.controller.treeActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.model.implementation.PackageNode;

import java.awt.event.ActionEvent;

public class NewPackageAction extends AbstractClassyAction {

    public NewPackageAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-package.png"));
        putValue(NAME, "New Package");
        putValue(SHORT_DESCRIPTION, "New Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MyNodeMutable selected = MainFrame.getInstance().getSelectedNode();
            if (selected.getClassyNode() instanceof PackageNode) {
                selected.getClassyNode().setPackageCheck(true);
                MyNodeMutable createdChild = MainFrame.getInstance().getClassyTree().addChild(selected, null);
                MainFrame.getInstance().getCurrentProject().setChanged(true);
            } else {
                AppCore.getInstance().showMessage(PossibleErr.PROJECT_OR_PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_PACKAGE);
            }
        } catch (NullPointerException exception) {
            AppCore.getInstance().showMessage(PossibleErr.PROJECT_OR_PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_PACKAGE);
            
        }

    }
}
