package controller;

import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import model.tree.MyNodeMutable;
import view.mainframe.MainFrame;
import model.repository.implementation.PackageNode;
import view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class NewDiagramAction extends AbstractClassyAction{
    public NewDiagramAction() {

        putValue(SMALL_ICON, loadIcon("/images/new-diagram.png"));
        putValue(NAME, "New Diagram");
        putValue(SHORT_DESCRIPTION, "New Diagram");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

            MyNodeMutable selected = MainFrame.getInstance().getSelectedNode();
            try {
                if (selected.getClassyNode() instanceof PackageNode) {
                    selected.getClassyNode().setPackageCheck(false);
                    MyNodeMutable createdChild = MainFrame.getInstance().getClassyTree().addChild(selected, null);
                    TabbedPane.getInstance().addNewPane(createdChild.getClassyNode().getName(), createdChild.getClassyNode().getId(), null);
                } else {
                    Message message = new Message(PossibleErr.PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_DIAGRAM);
                    MessageGenerator msggenerator = new MessageGenerator();
                    msggenerator.generateMsg(message);
                }
            } catch (NullPointerException exception) {
                Message message = new Message(PossibleErr.PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_DIAGRAM);
                MessageGenerator msggenerator = new MessageGenerator();
                msggenerator.generateMsg(message);
            }

    }
}
