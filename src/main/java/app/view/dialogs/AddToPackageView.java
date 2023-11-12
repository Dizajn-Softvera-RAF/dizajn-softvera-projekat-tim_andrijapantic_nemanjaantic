package app.view.dialogs;

import app.controller.NewDiagramAction;
import app.controller.NewPackageAction;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddToPackageView {
    NewDiagramAction newDiagramAction;
    NewPackageAction newPackageAction;
    public AddToPackageView() {
        JDialog typePicker = new JDialog(MainFrame.getInstance(), "Create in package:");
        typePicker.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ImageIcon packageIcon = new ImageIcon(getClass().getResource("/images/new-package.png"));
        ImageIcon diagram = new ImageIcon(getClass().getResource("/images/new-diagram.png"));
        ImageIcon packageIconSelected= new ImageIcon(getClass().getResource("/images/new-package-selected.png"));
        ImageIcon diagramSelected= new ImageIcon(getClass().getResource("/images/new-diagram-selected.png"));
        JLabel packageLabel = new JLabel("Package", packageIcon, JLabel.LEFT);
        JLabel diagramLabel = new JLabel("Diagram", diagram, JLabel.RIGHT);
        JLabel container = new JLabel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        diagramLabel.setVerticalTextPosition(JLabel.BOTTOM);
        diagramLabel.setHorizontalTextPosition(JLabel.CENTER);
        packageLabel.setVerticalTextPosition(JLabel.BOTTOM);
        packageLabel.setHorizontalTextPosition(JLabel.CENTER);
        packageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                newPackageAction = MainFrame.getInstance().getActionManager().getNewPackageAction();
                newPackageAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
               /* newDiagramAction = MainFrame.getInstance().getActionManager().getNewDiagramAction();
                // Zovemo actionPerformed method iz NewDiagramAction
                newDiagramAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));*/
                typePicker.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                packageLabel.setIcon(packageIconSelected);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                packageLabel.setIcon(packageIcon);
            }
        });
        diagramLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newDiagramAction = MainFrame.getInstance().getActionManager().getNewDiagramAction();
                // Zovemo actionPerformed method iz NewDiagramAction
                newDiagramAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
                typePicker.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                diagramLabel.setIcon(diagramSelected);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                diagramLabel.setIcon(diagram);
            }
        });
        container.add(Box.createRigidArea(new Dimension(20, 0)));
        container.add(packageLabel);
        container.add(Box.createRigidArea(new Dimension(20, 0)));
        typePicker.setIconImage(new ImageIcon(getClass().getResource("/images/picker.png")).getImage());
        container.add(diagramLabel);
        typePicker.add(container);
        typePicker.setSize(175, 130);
        typePicker.setVisible(true);
        typePicker.setLocationRelativeTo(null);
    }

}
