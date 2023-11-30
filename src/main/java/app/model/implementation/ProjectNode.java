package app.model.implementation;

import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.event.NotificationType;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

public class ProjectNode extends ClassyNodeComposite<PackageNode> implements IPublisher {
    String author;
    String path;

    public ProjectNode(String name, ProjectExplorer parent) {
        super(name, parent);
    }

    public ProjectNode() {
    }

    public static void prodjiKrozDecu(MyNodeMutable node) {

        int brojDece = node.getChildCount();

        for (int i = 0; i < brojDece; i++) {

            MyNodeMutable childNode = (MyNodeMutable) node.getChildAt(i);
            MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, childNode.getClassyNode().getId()));

            prodjiKrozDecu(childNode);
        }
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
        prodjiKrozDecu(MainFrame.getInstance().getSelectedNode());
        TabbedPane.getInstance().setTrenutniPaket(null);
        TabbedPane.getInstance().setPackageView(null);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {

    }

    @Override
    public void removeSubscriber(ISubscriber sub) {

    }

    @Override
    public void notifySubscribers(Notification notification) {

    }
}
