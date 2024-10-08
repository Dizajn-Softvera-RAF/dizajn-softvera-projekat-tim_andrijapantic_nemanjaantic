package app.view.popups;

import app.model.message.ErrType;
import app.model.message.Message;
import app.view.mainframe.MainFrame;

import javax.swing.*;

public class MessagePane extends JOptionPane {

    public MessagePane(Message m) {
        if (m.getType().equals(ErrType.ERROR)) {
            MessagePane.showMessageDialog(MainFrame.getInstance(), m.getStringContent(), "Error", JOptionPane.ERROR_MESSAGE);
        } else if (m.getType().equals(ErrType.INFO)) {
            MessagePane.showMessageDialog(MainFrame.getInstance(), m.getStringContent(), "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (m.getType().equals(ErrType.WARNING)) {
            MessagePane.showMessageDialog(MainFrame.getInstance(), m.getStringContent(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
