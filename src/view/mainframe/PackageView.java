package view.mainframe;

import model.event.ISubscriber;
import model.event.Notification;
import model.event.NotificationType;
import model.tree.MyNodeMutable;
import model.repository.composite.AbstractClassyNode;
import model.repository.implementation.PackageNode;
import model.repository.implementation.ProjectExplorer;
import model.repository.implementation.ProjectNode;
import view.tabs.TabbedPane;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class PackageView extends JPanel implements ISubscriber {
    JLabel autorJe = new JLabel();
    JLabel projekatJe = new JLabel();
    JLabel imeAutora = new JLabel("NoInput");
    JLabel imeProjekta = new JLabel("No Project Selected");
    ProjectNode projectNode;
    PackageNode packageNode;

    public PackageView() {
        projectNode = new ProjectNode();
        autorJe.setText("Autor: ");
        projekatJe.setText("Projekat: ");

        MainFrame.getInstance().getClassyTree().getTreeView().addSubscriber(this);
        JPanel containerPanel = new JPanel();
        containerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        containerPanel.setSize(100, 50);

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
            projectNode.setAuthor(notification.getTitle());
            imeAutora.setText(projectNode.getAuthor());
        } else if (notification.getType().equals(NotificationType.NODE_SELECTION_CHANGED)) {
            MyNodeMutable selected = notification.getNode();
            if (selected != null) {
                if (!(selected.getClassyNode() instanceof ProjectExplorer)) {
                    if (selected.getClassyNode() instanceof ProjectNode) {
                        projectNode = (ProjectNode) selected.getClassyNode();
                        if (projectNode.getAuthor() != null)
                            imeAutora.setText(projectNode.getAuthor());
                        else
                            imeAutora.setText("NoInput");
                    } else {
                        AbstractClassyNode currentNode = selected.getClassyNode();
                        while (!(currentNode instanceof ProjectNode)) {
                            AbstractClassyNode temp = currentNode;
                            temp = currentNode.getParent();
                            currentNode = temp;
                        }
                        projectNode = (ProjectNode) currentNode;
                    }
                    imeProjekta.setText(projectNode.getName());
                }
            } else {
                imeProjekta.setText("No Project Selected");
                imeAutora.setText("NoInput");
            }
        } else if (notification.getType().equals(NotificationType.PACKAGE_SELECTED)) {
            TabbedPane.getInstance().setTrenutniPaket((PackageNode) notification.getNode().getClassyNode());
            packageNode = (PackageNode) notification.getNode().getClassyNode();

        }

    }
}
