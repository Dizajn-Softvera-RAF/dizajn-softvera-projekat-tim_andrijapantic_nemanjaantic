package app.model.implementation;

import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import app.view.tabs.TabbedPane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.File;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DiagramNode.class, name = "DiagramNode"),
        @JsonSubTypes.Type(value = PackageNode.class, name = "PackageNode"),
})
public class PackageNode extends ClassyNodeComposite<ClassyNodeComposite> implements IPublisher {
    @JsonIgnore
    ArrayList<ISubscriber> subscribers;

    public PackageNode() {
    }

    public PackageNode(String name, AbstractClassyNode parent) {
        super(name, parent);
        subscribers = new ArrayList<>();
    }

    public static void prodjiKrozDecu(MyNodeMutable node) {
        int brojDece = node.getChildCount();
        for (int i = 0; i < brojDece; i++) {
            MyNodeMutable childNode = (MyNodeMutable) node.getChildAt(i);
            MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, childNode.getClassyNode().getId()));
            prodjiKrozDecu(childNode);
        }
    }

    public void generateCodeStructure(String path) {
        File packageFolder = new File(path, getName().toLowerCase());
        packageFolder.mkdirs();
        for (ClassyNodeComposite child : getChildren()) {
            if (child instanceof PackageNode) {
                ((PackageNode)child).generateCodeStructure(packageFolder.getAbsolutePath());
            } else if (child instanceof DiagramNode) {
                ((DiagramNode)child).generateFile(packageFolder.getAbsolutePath());
            }
        }
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof DiagramNode) {
            DiagramNode node = (DiagramNode) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
            }
        } else if (child instanceof PackageNode) {
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
        MainFrame.getInstance().setPackageView(null);
        this.getChildren().clear();
    }

    public void removeChild(DiagramNode diagramNode) {
        this.getChildren().remove(diagramNode);
    }


    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (ISubscriber subscriber : subscribers) {

            subscriber.update(notification);
        }
    }
}
