package app.model.repository.implementation;

import app.model.logger.event.Notification;
import app.model.repository.composite.ClassyNodeComposite;
import app.model.logger.event.NotificationType;
import app.model.repository.composite.AbstractClassyNode;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;

public class PackageNode extends ClassyNodeComposite<DiagramNode> {


    public PackageNode() {
    }

    public PackageNode(String name, AbstractClassyNode parent) {
        super(name, parent);
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
        if (child instanceof DiagramNode) {
            DiagramNode node = (DiagramNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        }
    }

    @Override
    public void removeChildren() {
        prodjiKrozDecu(MainFrame.getInstance().getSelectedNode());
        TabbedPane.getInstance().setTrenutniPaket(null);
    }
}
