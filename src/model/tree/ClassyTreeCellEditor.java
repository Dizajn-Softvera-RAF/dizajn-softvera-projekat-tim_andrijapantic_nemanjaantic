package model.tree;

import model.event.Notification;
import model.event.NotificationType;
import model.message.Message;
import model.message.MessageGenerator;
import model.message.PossibleErr;
import view.tree.ClassyTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor  extends DefaultTreeCellEditor implements ActionListener {


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


        if (!e.getActionCommand().equals("")) {
            MyNodeMutable clicked = (MyNodeMutable) clickedOn;
            clicked.getClassyNode().setName(e.getActionCommand());
            Notification notification = new Notification(NotificationType.RENAME, clicked.getClassyNode().getName());

            notification.setId(clicked.getClassyNode().getId());

            ClassyTreeView mapTreeView = (ClassyTreeView) tree;
            mapTreeView.notifySubscribers(notification);
        }

        else {
            Message message = new Message(PossibleErr.NAME_CANNOT_BE_EMPTY);
            MessageGenerator msggenerator = new MessageGenerator();
            msggenerator.generateMsg(message);
        }



    }

}
