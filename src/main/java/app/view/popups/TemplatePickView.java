package app.view.popups;

import app.core.AppCore;
import app.model.diagcomposite.Interclass;
import app.model.implementation.DiagramNode;
import app.model.implementation.ProjectNode;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.util.Random;


public class TemplatePickView extends JDialog{

    public TemplatePickView() {
        init();
    }

    private void init() {
        File templatesFolder = new File("src/main/resources/templates/");
        File[] templates = templatesFolder.listFiles();
        String[] templateNames = new String[templates.length];
        for (int i=0; i<templates.length; i++) {
            templateNames[i] = templates[i].getName();
        }
        JComboBox<String> templateList = new JComboBox<>(templateNames);
        JLabel lbl = new JLabel("Pick a Template From The List: ");

        JButton chooseBttn = new JButton("Create a Diagram");
        JButton cancelBttn = new JButton("Cancel");

        JPanel templatePanel = new JPanel();
        templatePanel.setLayout(new FlowLayout());
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        JLabel empty_line1 = new JLabel("");
        empty_line1.setPreferredSize(new Dimension(3000,0));


        chooseBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedTemplateIndex = templateList.getSelectedIndex();
                if (selectedTemplateIndex!=-1) {
                    String fileName = (String) templateList.getSelectedItem();
                    File selectedTemplate = new File(templatesFolder, fileName);
                    String filePath = selectedTemplate.getAbsolutePath();
                    System.out.println("Selektovan template: " + filePath);
                    DiagramNode d = AppCore.getInstance().getSerializer().loadDiagram(selectedTemplate);
                    d.setName("Diagram" + new Random().nextInt(1000));
                    MainFrame.getInstance().getClassyTree().loadTemplate(d);
                }
                dispose();
            }
        });

        cancelBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        container.add(Box.createRigidArea(new Dimension(10, 0)));
        container.add(chooseBttn);
        container.add(Box.createRigidArea(new Dimension(10, 0)));
        container.add(cancelBttn);
        container.add(Box.createRigidArea(new Dimension(10, 0)));

        templatePanel.add(lbl);
        templatePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        templatePanel.add(empty_line1);
        templatePanel.add(templateList);
        templatePanel.add(Box.createRigidArea(new Dimension(10, 10)));
        templatePanel.add(container);
        templatePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(templatePanel);

        setTitle("Pick a Template");
        setSize(285, 130);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/images/picker.png")).getImage());
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
