package app.view.dialogs;

import app.model.classcontent.Attribute;
import app.model.classcontent.ClassContent;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagcomposite.DiagramElement;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class EditView extends JFrame {

    private JTextField nameTextField;
    private DiagramView diagramView;

    public EditView(Interclass element, DiagramView diagramView) {
        this.diagramView = diagramView;
        setTitle("Edit Content");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        init(element);
        setSize(600, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void init(Interclass element) {
        JLabel changeName = new JLabel("Change Interclass Name:");
        nameTextField = new JTextField();
        nameTextField.setText(element.getName());

        JLabel container = new JLabel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        container.add(changeName);
        container.add(nameTextField);

        JButton changeNameButton = new JButton("Change Name");
        JButton addContentButton = new JButton("Add Content");

        JLabel empty_line1 = new JLabel("");
        empty_line1.setPreferredSize(new Dimension(3000,0));
        JLabel empty_line2 = new JLabel("");
        empty_line2.setPreferredSize(new Dimension(3000,0));
        JLabel empty_line3 = new JLabel("");
        empty_line3.setPreferredSize(new Dimension(3000,0));
        JLabel empty_line4 = new JLabel("");
        empty_line4.setPreferredSize(new Dimension(3000,0));
        JLabel empty_line5 = new JLabel("");
        empty_line5.setPreferredSize(new Dimension(3000,0));
        JLabel empty_line6 = new JLabel("");
        empty_line6.setPreferredSize(new Dimension(3000,0));
        JLabel empty_line7 = new JLabel("");
        empty_line7.setPreferredSize(new Dimension(3000,0));

        JLabel addNewContent = new JLabel("Add New Content:");
        JTextField newNameField = new JTextField();
        newNameField.setText("Enter Name here");
        JTextField newTypeField = new JTextField();
        newTypeField.setText("Enter Type here");

        String contentType[];
        String visibilities[] = new String[2];
        visibilities[0] = "Private";
        visibilities[1] = "Public";


        if (element instanceof Klasa) {
            contentType = new String[2];
            contentType[0] = "Attribute";
            contentType[1] = "Method";
        } else if (element instanceof Interface) {
            contentType = new String[1];
            contentType[0] = "Method";
        } else {
            contentType = new String[1];
            contentType[0] = "Enumerable";
        }

        String contentList1[] = new String[element.getContent().size()];
        populateBox(contentList1, element.getContent());

        JLabel deleteContentLbl = new JLabel("Delete Selected Content:");
        JButton deleteContentButton = new JButton("Delete Content");

        String contentList2[] = new String[element.getContent().size()];
        populateBox(contentList2, element.getContent());
        JLabel changeContentLbl = new JLabel("Change Selected Content:");
        JButton changeContentButton = new JButton("Change Content");
        JTextField changeNameField = new JTextField();
        changeNameField.setText("Enter New Name Here");
        JTextField changeTypeField = new JTextField();
        changeTypeField.setText("Enter New Type Here");

        JComboBox contentTypeBox=new JComboBox(contentType);
        JComboBox visibilityBox=new JComboBox(visibilities);
        JComboBox contentListBox1 = new JComboBox(contentList1);
        JComboBox contentListBox2 = new JComboBox(contentList2);
        JComboBox changeVisibilityBox = new JComboBox(visibilities);

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setName(nameTextField.getText());
                diagramView.repaint();
            }
        });

        addContentButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String t = (String) contentTypeBox.getSelectedItem();
               if (t!=null) {
                   if (t.equals("Method")) {
                       Visibility visibility;
                       if (visibilityBox.getSelectedItem().equals("Private"))
                           visibility = Visibility.PRIVATE;
                       else
                           visibility = Visibility.PUBLIC;
                       Method metoda = new Method(newNameField.getText(), newTypeField.getText(), visibility);
                       System.out.println("Napravila se metoda: "  + metoda.getMethodString());
                       element.getContent().add(metoda);
                       contentListBox1.addItem(metoda.getMethodString());
                       contentListBox2.addItem(metoda.getMethodString());
                       diagramView.repaint();
                   } else if (t.equals("Attribute")) {
                       Visibility visibility;
                       if (visibilityBox.getSelectedItem().equals("Private"))
                           visibility = Visibility.PRIVATE;
                       else
                           visibility = Visibility.PUBLIC;
                       Attribute attribute = new Attribute(newNameField.getText(), newTypeField.getText(), visibility);
                       System.out.println("Napravio se atribut: "  + attribute.getAttributeString());
                       element.getContent().add(attribute);
                       contentListBox1.addItem(attribute.getAttributeString());
                       contentListBox2.addItem(attribute.getAttributeString());
                       diagramView.repaint();
                   } else if (t.equals("Enumerable")) {
                       EnumType enumerable = new EnumType(newNameField.getText());
                       System.out.println("Napravio se enumerable: "  + enumerable.getEnumerableString());
                       element.getContent().add(enumerable);
                       contentListBox1.addItem(enumerable.getEnumerableString());
                       contentListBox2.addItem(enumerable.getEnumerableString());
                       diagramView.repaint();
                   }
               } else {
                   System.out.println("Nista selektovano");
               }

           }
        });
        deleteContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexContenta = contentListBox1.getSelectedIndex();
                contentListBox1.removeItemAt(indexContenta);
                contentListBox2.removeItemAt(indexContenta);
                element.getContent().remove(indexContenta);
                diagramView.repaint();
            }
        });
        changeContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t = (String) contentTypeBox.getSelectedItem();
                if (t!=null) {
                    if (t.equals("Method")) {
                        Visibility visibility;
                        if (visibilityBox.getSelectedItem().equals("Private"))
                            visibility = Visibility.PRIVATE;
                        else
                            visibility = Visibility.PUBLIC;
                        Method metoda = new Method(newNameField.getText(), newTypeField.getText(), visibility);
                        System.out.println("Napravila se metoda: "  + metoda.getMethodString());
                        element.getContent().add(metoda);
                        contentListBox1.addItem(metoda.getMethodString());
                        contentListBox2.addItem(metoda.getMethodString());
                        diagramView.repaint();
                    } else if (t.equals("Attribute")) {
                        Visibility visibility;
                        if (visibilityBox.getSelectedItem().equals("Private"))
                            visibility = Visibility.PRIVATE;
                        else
                            visibility = Visibility.PUBLIC;
                        Attribute attribute = new Attribute(newNameField.getText(), newTypeField.getText(), visibility);
                        System.out.println("Napravio se atribut: "  + attribute.getAttributeString());
                        element.getContent().add(attribute);
                        contentListBox1.addItem(attribute.getAttributeString());
                        contentListBox2.addItem(attribute.getAttributeString());
                        diagramView.repaint();
                    } else if (t.equals("Enumerable")) {
                        EnumType enumerable = new EnumType(newNameField.getText());
                        System.out.println("Napravio se enumerable: "  + enumerable.getEnumerableString());
                        element.getContent().add(enumerable);
                        contentListBox1.addItem(enumerable.getEnumerableString());
                        contentListBox2.addItem(enumerable.getEnumerableString());
                        diagramView.repaint();
                    }
                } else {
                    System.out.println("Nista selektovano");
                }
            }
        });

        nameTextField.setPreferredSize(new Dimension(200,20));
        newTypeField.setPreferredSize(new Dimension(100,20));
        newNameField.setPreferredSize(new Dimension(100,20));
        changeTypeField.setPreferredSize(new Dimension(150,20));
        changeNameField.setPreferredSize(new Dimension(150,20));

        add(changeName);
        add(empty_line1);
        add(nameTextField);
        add(changeNameButton);
        add(empty_line2);
        add(addNewContent);
        add(empty_line3);
        add(contentTypeBox);
        add(newNameField);
        if (element instanceof Klasa || element instanceof Interface) {
            add(newTypeField);
            add(visibilityBox);
        }
        add(addContentButton);
        add(empty_line4);
        add(deleteContentLbl);
        add(empty_line5);
        add(contentListBox1);
        add(deleteContentButton);
        add(empty_line6);
        add(changeContentLbl);
        add(empty_line7);
        add(contentListBox2);
        add(changeNameField);
        add(changeTypeField);
        add(changeVisibilityBox);
        add(changeContentButton);
    }

    public void populateBox(String[] content, List<ClassContent> classContents) {
        int i = 0;
        for (ClassContent classContent: classContents) {
            if (classContent instanceof Attribute)
                content[i] = ((Attribute) classContent).getAttributeString();
            if (classContent instanceof Method)
                content[i] = ((Method) classContent).getMethodString();
            if (classContent instanceof EnumType)
                content[i] = ((EnumType) classContent).getEnumerableString();
            i++;
        }
    }

}
