package view.mainframe;

import view.tabs.TabbedPane;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class PackageView extends JPanel {
    JLabel autorJe = new JLabel();
    JLabel projekatJe = new JLabel();
    JLabel imeAutora = new JLabel("AutorSample");
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
}
