package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import model.tree.MyNodeMutable;
import view.mainframe.MainFrame;
import model.repository.implementation.PackageNode;
import model.repository.implementation.ProjectNode;

import java.awt.event.ActionEvent;

public class NewPackageAction extends AbstractClassyAction{

     boolean packageCheck = false;

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


            } else {
                Message message = new Message(PossibleErr.PROJECT_OR_PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_PACKAGE);
                MessageGenerator msggenerator = new MessageGenerator();
                msggenerator.generateMsg(message);
            }
        } catch (NullPointerException exception) {
            Message message = new Message(PossibleErr.PROJECT_OR_PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_PACKAGE);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);
        }

    }

    public boolean isPackageCheck() {
        return packageCheck;
    }

    public void setPackageCheck(boolean packageCheck) {
        this.packageCheck = packageCheck;
    }
}
