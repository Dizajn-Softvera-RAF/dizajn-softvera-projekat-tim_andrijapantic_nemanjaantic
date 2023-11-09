package view.tabs;

import diagramActions.CloseTabAction;
import model.event.ISubscriber;
import model.event.Notification;
import model.event.NotificationType;
import model.tree.MyNodeMutable;
import view.mainframe.DiagramView;
import view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.UUID;

public class Tab  implements ISubscriber {
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

    @Override
    public void update(Notification notification) {
        if (notification.getType().equals(NotificationType.DELETE_DIAGRAM)) {
            //try {
                System.out.println("Dobio sam delete diagram poruku!~");
                if (this.getId().equals(notification.getId())) {
                    MainFrame.getInstance().getClassyTree().getTreeView().removeSubscriber(this);
                    parent.getListaTabova().remove(this);
                    parent.removeTab(title, id);
                }
         //   } catch (ConcurrentModificationException exception) {}


        } else if (notification.getType().equals(NotificationType.DELETE_PACKAGE)) {
            System.out.println("Dobio sam delete package poruku!~");
            int counter = notification.getNode().getChildCount();
            System.out.println("Deca koju treba da obrisem: " + counter);
            System.out.println(notification.getNode().getChildCount());
            for (int i = 0; i < counter; i = i + 1) {

                MyNodeMutable checkForDeletion = (MyNodeMutable) notification.getNode().getChildAt(i);
                if (this.getId().equals(checkForDeletion.getClassyNode().getId())) {
                    parent.getTrenutniTaboviZaBrisanje().add(this);
                }

            }
        }
        else if (notification.getType().equals(NotificationType.DELETE_PROJECT)){


        } else if (notification.getType().equals(NotificationType.RENAME)) {

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
}
