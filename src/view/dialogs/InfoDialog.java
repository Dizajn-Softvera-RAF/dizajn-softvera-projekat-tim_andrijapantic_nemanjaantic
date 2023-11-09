package view.dialogs;

import view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {
    public InfoDialog() {
        ImageIcon i1 = new ImageIcon(getClass().getResource("/images/Andrija.jpg"));
        ImageIcon i2 = new ImageIcon(getClass().getResource("/images/Nemanja.png"));
        JLabel textArea = new JLabel("Andrija Pantic 33/21 RN", i1, JLabel.LEFT);
        JLabel textArea2 = new JLabel("Nemanja Antic 48/21 RN", i2, JLabel.RIGHT);
        JLabel container = new JLabel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        textArea.setVerticalTextPosition(JLabel.BOTTOM);
        textArea.setHorizontalTextPosition(JLabel.CENTER);
        textArea2.setVerticalTextPosition(JLabel.BOTTOM);
        textArea2.setHorizontalTextPosition(JLabel.CENTER);

        container.add(Box.createRigidArea(new Dimension(10, 0)));
        container.add(textArea);
        container.add(Box.createRigidArea(new Dimension(10, 0)));
        container.add(textArea2);

        add(container);

        setTitle("About us");
        setIconImage(new ImageIcon(getClass().getResource("/images/info.png")).getImage());
        setSize(455, 330);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
