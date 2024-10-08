package app.controller.treeActions;

import app.controller.AbstractClassyAction;
import app.core.AppCore;
import app.model.composite.ClassyNodeComposite;
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
                ((DiagramNode)createdChild.getClassyNode()).setMyNodeMutable(createdChild);
                TabbedPane.getInstance().addNewPane(createdChild.getClassyNode().getName(), createdChild.getClassyNode().getId(), (DiagramNode)createdChild.getClassyNode(), createdChild);
                if (TabbedPane.getInstance().getTrenutniPaket() == null)
                    TabbedPane.getInstance().closeAllTabs();
                else if (!TabbedPane.getInstance().getTrenutniPaket().getId().equals(selected.getClassyNode().getId())) {
                    TabbedPane.getInstance().closeAllTabs();
                    PackageNode paket = TabbedPane.getInstance().getTrenutniPaket();
                    for (ClassyNodeComposite diagramNode : paket.getChildren()) {
                        if (diagramNode instanceof DiagramNode) {
                            if (!TabbedPane.getInstance().isTabPresent(diagramNode.getName())) {
                                TabbedPane.getInstance().addNewPane(diagramNode.getName(), diagramNode.getId(), ((DiagramNode)diagramNode), ((DiagramNode)diagramNode).getMyNodeMutable());
                            }
                        }

                    }
                }
                MainFrame.getInstance().getCurrentProject().setChanged(true);
            } else {
                AppCore.getInstance().showMessage(PossibleErr.PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_DIAGRAM);
                
            }
        } catch (NullPointerException exception) {
            AppCore.getInstance().showMessage(PossibleErr.PACKAGE_MUST_BE_SELECTED_TO_CREATE_A_DIAGRAM);
            
        }

    }
}
