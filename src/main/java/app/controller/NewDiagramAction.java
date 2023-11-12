package app.controller;

import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.model.implementation.DiagramNode;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.model.implementation.PackageNode;
import app.view.tabs.TabbedPane;

import java.awt.event.ActionEvent;

public class NewDiagramAction extends AbstractClassyAction {
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
                if (TabbedPane.getInstance().getTrenutniPaket() == null)
                    TabbedPane.getInstance().closeAllTabs();
                else if (!TabbedPane.getInstance().getTrenutniPaket().getId().equals(selected.getClassyNode().getId())) {
                    TabbedPane.getInstance().closeAllTabs();
                    PackageNode paket = TabbedPane.getInstance().getTrenutniPaket();
                    for (DiagramNode diagramNode : paket.getChildren()) {
                        if (!TabbedPane.getInstance().isTabPresent(diagramNode.getName())) {
                            TabbedPane.getInstance().addNewPane(diagramNode.getName(), diagramNode.getId(), MainFrame.getInstance().getSelectedNode());
                        }
                    }
                }
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
