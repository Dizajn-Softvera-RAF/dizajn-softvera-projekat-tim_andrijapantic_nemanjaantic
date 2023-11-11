package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import model.tree.MyNodeMutable;
import view.dialogs.AddToPackageView;
import view.mainframe.MainFrame;
import model.repository.implementation.PackageNode;

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
                MainFrame.getInstance().getSelectedNode().getChildren().add(createdChild);
                if (!MainFrame.getInstance().getMyNodeMutables().contains(MainFrame.getInstance().getSelectedNode()))
                    MainFrame.getInstance().getMyNodeMutables().add(MainFrame.getInstance().getSelectedNode());
            }
        } catch (NullPointerException exception) {
            Message message = new Message(PossibleErr.PROJECT_EXPLORER_MUST_BE_SELECTED_TO_CREATE_A_PROJECT);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);
        }
    }
}
