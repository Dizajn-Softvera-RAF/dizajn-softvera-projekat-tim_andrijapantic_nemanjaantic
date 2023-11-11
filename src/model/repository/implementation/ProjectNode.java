package model.repository.implementation;

import model.event.Notification;
import model.event.NotificationType;
import model.repository.composite.AbstractClassyNode;
import model.repository.composite.ClassyNodeComposite;
import model.tree.MyNodeMutable;
import view.mainframe.MainFrame;
import view.tabs.Tab;
import view.tabs.TabbedPane;

public class ProjectNode extends ClassyNodeComposite<PackageNode> {
    String author;

    public ProjectNode(String name, ProjectExplorer parent) {
        super(name, parent);
    }

    public ProjectNode() {
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof PackageNode) {
            PackageNode node = (PackageNode) child;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
