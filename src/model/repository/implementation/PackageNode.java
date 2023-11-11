package model.repository.implementation;

import main.Main;
import model.event.Notification;
import model.event.NotificationType;
import model.repository.composite.AbstractClassyNode;
import model.repository.composite.ClassyNodeComposite;
import model.tree.MyNodeMutable;
import view.mainframe.MainFrame;
import view.tabs.Tab;
import view.tabs.TabbedPane;

public class PackageNode extends ClassyNodeComposite<DiagramNode> {


    public PackageNode() {
    }

    public PackageNode(String name, AbstractClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof DiagramNode) {
            DiagramNode node = (DiagramNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }
    }

    @Override
    public void removeChildren() {
        for (MyNodeMutable myNodeMutable : MainFrame.getInstance().getMyNodeMutables()) {
            if (myNodeMutable.getClassyNode().getId().equals(this.getId())) {
                for (MyNodeMutable myNodeMutableChild : myNodeMutable.getChildren()) {
                    MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, myNodeMutableChild.getClassyNode().getId()));
                }
            }
        }
        TabbedPane.getInstance().setTrenutniPaket(null);

        for (Tab tab : TabbedPane.getInstance().getTrenutniTaboviZaBrisanje()) {
            MainFrame.getInstance().getClassyTree().getTreeView().removeSubscriber(tab);
        }
        TabbedPane.getInstance().getTrenutniTaboviZaBrisanje().clear();
        this.getChildren().clear();
    }
}
