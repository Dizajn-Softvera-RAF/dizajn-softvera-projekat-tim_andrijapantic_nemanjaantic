package app.model.tree;

import app.model.logger.event.Notification;
import app.model.logger.event.NotificationType;
import app.model.message.Message;
import app.model.repository.implementation.DiagramNode;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.view.tabs.TabbedPane;
import app.view.tree.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn = null;
    private JTextField edit = null;


    public ClassyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        super.getTreeCellEditorComponent(arg0, arg1, arg2, arg3, arg4, arg5);
        clickedOn = arg1;
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }


    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent) arg0).getClickCount() == 3) {
                return true;
            }
        return false;
    }


    public void actionPerformed(ActionEvent e) {

        if (!(clickedOn instanceof MyNodeMutable))
            return;

        // TRIPLE CLICK
        if (!e.getActionCommand().equals("")) {
            MyNodeMutable clicked = (MyNodeMutable) clickedOn;
            clicked.getClassyNode().setName(e.getActionCommand());
            Notification notification = new Notification(NotificationType.RENAME, clicked.getClassyNode().getId(), clicked.getClassyNode().getName());

            notification.setId(clicked.getClassyNode().getId());

            ClassyTreeView mapTreeView = (ClassyTreeView) tree;
            mapTreeView.notifySubscribers(notification);

            if (clicked.getClassyNode() instanceof DiagramNode) {

                TabbedPane.getInstance().removeTab(clicked.getClassyNode().getName(), clicked.getClassyNode().getId());
                TabbedPane.getInstance().addNewPane(clicked.getClassyNode().getName(), clicked.getClassyNode().getId(), clicked);

                int tabIndex = TabbedPane.getInstance().indexOfTab(clicked.getClassyNode().getName());
                if (tabIndex != -1) {
                    TabbedPane.getInstance().setSelectedIndex(tabIndex);
                }
            }


        } else {
            Message message = new Message(PossibleErr.NAME_CANNOT_BE_EMPTY);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);
        }


    }

}
