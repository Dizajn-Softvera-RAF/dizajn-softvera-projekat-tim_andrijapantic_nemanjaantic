package app.view.popups;

import app.controller.NewDiagramAction;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DiagramCreationView extends JDialog {
    public DiagramCreationView() {
        init();
    }
    private void init() {

        ImageIcon blankDiagram = new ImageIcon(getClass().getResource("/images/blank_diagram-icon.png"));
        ImageIcon template = new ImageIcon(getClass().getResource("/images/template-icon.png"));
        ImageIcon blankSelected= new ImageIcon(getClass().getResource("/images/blank_diagram-icon-selected.png"));
        ImageIcon templateSelected= new ImageIcon(getClass().getResource("/images/template-icon-selected.png"));
        JLabel blankLabel = new JLabel("Blank Diagram", blankDiagram, JLabel.LEFT);
        JLabel templateLabel = new JLabel("Pick a Template", template, JLabel.RIGHT);
        JLabel container = new JLabel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        blankLabel.setVerticalTextPosition(JLabel.BOTTOM);
        blankLabel.setHorizontalTextPosition(JLabel.CENTER);
        templateLabel.setVerticalTextPosition(JLabel.BOTTOM);
        templateLabel.setHorizontalTextPosition(JLabel.CENTER);

        blankLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NewDiagramAction newDiagramAction = MainFrame.getInstance().getActionManager().getNewDiagramAction();
                newDiagramAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                blankLabel.setIcon(blankSelected);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                blankLabel.setIcon(blankDiagram);
            }
        });
        templateLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new TemplatePickView();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                templateLabel.setIcon(templateSelected);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                templateLabel.setIcon(template);
            }
        });
        container.add(Box.createRigidArea(new Dimension(20, 0)));
        container.add(blankLabel);
        container.add(Box.createRigidArea(new Dimension(20, 0)));
        container.add(templateLabel);

        setTitle("Choose a Diagram Type");
        setIconImage(new ImageIcon(getClass().getResource("/images/picker.png")).getImage());
        add(container);
        setSize(205, 130);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
