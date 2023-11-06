package view.tabs;

import diagramActions.CloseTabAction;
import view.mainframe.DiagramView;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class Tab  {
    private TabbedPane parent;
    private String title;

    private UUID id;
    private JPanel header;

    private JLabel headerLabel;

    private JTextField authorField;
    private JPanel body;

    private DiagramView diagramView;




    public Tab(TabbedPane parent, String title, UUID id) {
        this.parent = parent;
        this.title = title;
        this.id = id;
        this.body = new JPanel();


        this.header = new JPanel(new GridBagLayout());
        this.header.setOpaque(false);
        headerLabel = new JLabel(title);
        JButton btnClose = new JButton("x");
        authorField = new JTextField();
        authorField.setText("Author");
        authorField.setOpaque(false);
        authorField.setEditable(true);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        this.header.add(headerLabel, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        this.header.add(btnClose, gbc);
        btnClose.addActionListener(new CloseTabAction(this));
        /**
        gbc.gridx--;
        gbc.gridy++;
        gbc.weighty = 1;
        this.header.add(new JLabel("Author:"), gbc);

        gbc.gridx++;
        this.header.add(authorField, gbc);
        */

    }


    public UUID getId() {
        return id;
    }

    public TabbedPane getParent() {
        return parent;
    }

    public String getTitle() {
        return title;
    }

    public JPanel getBody() {
        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(JPanel body) {
        this.body = body;
    }

    public void setParent(TabbedPane parent) {
        this.parent = parent;
    }

    public DiagramView getDiagramView() {
        return diagramView;
    }

    public void setDiagramView(DiagramView mapView) {
        this.diagramView = mapView;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public void setAuthorField(JTextField authorField) {
        this.authorField = authorField;
    }

    public JPanel getHeader() {
        return header;
    }

    public void setHeader(JPanel header) {
        this.header = header;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
