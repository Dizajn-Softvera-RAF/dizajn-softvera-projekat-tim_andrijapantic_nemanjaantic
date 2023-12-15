package app.view.tabs;

import app.controller.diagramActions.CloseTabAction;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.implementation.PackageNode;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.DiagramView;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class Tab extends Component implements ISubscriber {
    private TabbedPane parent;
    private String title;

    private UUID id;
    private JPanel header;

    private JLabel headerLabel;

    private JTextField authorField;
    private JPanel body;

    private DiagramView diagramView;
    private boolean blocked = false;


    public Tab(TabbedPane parent, String title, UUID id) {
        this.parent = parent;
        this.title = title;
        this.id = id;
        this.body = new JPanel();


        this.header = new JPanel(new GridBagLayout());
        this.header.setOpaque(false);
        headerLabel = new JLabel();
        headerLabel.setText(title);
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

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TabbedPane getParent() {
        return parent;
    }

    public void setParent(TabbedPane parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JPanel getBody() {
        return body;
    }

    public void setBody(JPanel body) {
        this.body = body;
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

    @Override
    public void update(Notification notification) {
        if (notification.getType().equals(NotificationType.DELETE_DIAGRAM)) {

            if (this.getId().equals(notification.getId())) {
                TabbedPane.getInstance().getTrenutniTaboviZaBrisanje().add(this);
                TabbedPane.getInstance().getListaTabova().remove(this);
                TabbedPane.getInstance().removeTab(title, id);
                ((PackageNode)diagramView.getDiagramNode().getParent()).removeChild(diagramView.getDiagramNode());
            }


        } else if (notification.getType().equals(NotificationType.DELETE_PACKAGE)) {
            int counter = notification.getNode().getChildCount();
            System.out.println(notification.getNode().getChildCount());
            for (int i = 0; i < counter; i = i + 1) {

                MyNodeMutable checkForDeletion = (MyNodeMutable) notification.getNode().getChildAt(i);
                if (this.getId().equals(checkForDeletion.getClassyNode().getId())) {
                    parent.getTrenutniTaboviZaBrisanje().add(this);
                    TabbedPane.getInstance().getListaTabova().remove(this);
                }

            }
        }  else if (notification.getType().equals(NotificationType.RENAME)) {

            if (this.getId().equals(notification.getId())) {

                int count = parent.getTabCount();


                for (int i = 0; i < count; i++) {

                    if (parent.getTitleAt(i).equals(this.getTitle())) {

                        parent.setTitleAt(i, notification.getTitle());
                        headerLabel.setText(notification.getTitle());
                        headerLabel.revalidate();
                        headerLabel.repaint();
                    }
                }
                this.setTitle(notification.getTitle());
            }
        }


    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
