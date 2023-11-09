package view.mainframe;

import model.event.ISubscriber;
import model.event.Notification;
import model.event.NotificationType;
import view.tabs.TabbedPane;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class PackageView extends JPanel implements ISubscriber {
    JLabel autorJe = new JLabel();
    JLabel projekatJe = new JLabel();
    String imeZaAutora = "NoInput";
    JLabel imeAutora = new JLabel();
    JLabel imeProjekta = new JLabel("ProjekatSample");

    public PackageView() {
        autorJe.setText("Autor: ");
        projekatJe.setText("Projekat: ");

        JPanel containerPanel = new JPanel(); // Leave the layout to the default FlowLayout
        containerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        containerPanel.setSize(100,50);
        containerPanel.add(autorJe);
        containerPanel.add(imeAutora);
        containerPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        containerPanel.add(projekatJe);
        containerPanel.add(imeProjekta);

        setLayout(new BorderLayout());
        add(containerPanel, BorderLayout.PAGE_START);
        add(TabbedPane.getInstance(), BorderLayout.CENTER);
    }

    @Override
    public void update(Notification notification) {
        if (notification.getType().equals(NotificationType.CHANGE_AUTHOR)) {
            imeZaAutora = notification.getTitle();
        }
    }
}
