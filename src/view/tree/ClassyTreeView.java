package view.tree;

import model.event.IPublisher;
import model.event.ISubscriber;
import model.event.Notification;
import model.tree.ClassyTreeCellEditor;
import model.tree.ClassyTreeCellRenderer;
import model.tree.ClassyTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

public class ClassyTreeView extends JTree implements IPublisher {

    private List<ISubscriber> subscribers;

    public ClassyTreeView(DefaultTreeModel defaultTreeModel) {
        subscribers = new ArrayList<>();
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer ruTreeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        setCellEditor(new ClassyTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
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

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }
}
