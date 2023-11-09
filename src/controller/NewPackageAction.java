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

    public NewPackageAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-package.png"));
        putValue(NAME, "New Package");
        putValue(SHORT_DESCRIPTION, "New Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         *  OVO ISPOD JE TEST, DODACE SE VEROVATNO U NULLPOINTEREXCEPTION
         */
        try {
            MyNodeMutable selected = MainFrame.getInstance().getSelectedNode();
            if (selected.getClassyNode() instanceof ProjectNode || selected.getClassyNode() instanceof PackageNode) {

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
}
