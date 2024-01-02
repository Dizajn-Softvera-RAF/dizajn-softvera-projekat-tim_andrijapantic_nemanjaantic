package app.view.tree;

import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.tree.controller.ClassyTreeCellEditor;
import app.model.tree.ClassyTreeCellRenderer;
import app.model.tree.ClassyTreeSelectionListener;

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
