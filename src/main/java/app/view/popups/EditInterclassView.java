package app.view.popups;

import app.model.classcontent.Attribute;
import app.model.classcontent.ClassContent;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.view.mainframe.MainFrame;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class EditInterclassView extends JFrame {

    private JTextField nameTextField;
    private JComboBox contentListBox1;
    private JComboBox contentListBox2;

    public EditInterclassView(Interclass element) {
        setTitle("Edit Content");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        init(element);
        setSize(600, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/settings-icon2.png")).getImage());
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
        String visibilities[] = new String[3];
        visibilities[0] = "Private";
        visibilities[1] = "Public";
        visibilities[2] = "Protected";


        if (element instanceof Klasa) {
            contentType = new String[2];
            contentType[0] = "Attribute";
            contentType[1] = "Method";
        } else if (element instanceof Interface) {
            contentType = new String[1];
            contentType[0] = "Method";
        } else {
            contentType = new String[1];
            contentType[0] = "EnumType";
        }

        List<String> contentList1 = new ArrayList<>();
        populateBox(contentList1, element.getContent());

        JLabel deleteContentLbl = new JLabel("Delete Selected Content:");
        JButton deleteContentButton = new JButton("Delete Content");

        List<String> contentList2 = new ArrayList<>();
        populateBox(contentList2, element.getContent());
        JLabel changeContentLbl = new JLabel("Change Selected Content:");
        JButton changeContentButton = new JButton("Change Content");
        JTextField changeNameField = new JTextField();
        changeNameField.setText("Enter New Name Here");
        JTextField changeTypeField = new JTextField();
        changeTypeField.setText("Enter New Type Here");

        JComboBox contentTypeBox=new JComboBox(contentType);
        JComboBox visibilityBox=new JComboBox(visibilities);
        contentListBox1 = new JComboBox(contentList1.toArray());
        contentListBox2 = new JComboBox(contentList2.toArray()          );
        JComboBox changeVisibilityBox = new JComboBox(visibilities);

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setName(nameTextField.getText());
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
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
                       else if (visibilityBox.getSelectedItem().equals("Public"))
                           visibility = Visibility.PUBLIC;
                       else
                           visibility = Visibility.PROTECTED;
                       Method metoda = new Method(newNameField.getText(), newTypeField.getText(), visibility);
                       System.out.println("Napravila se metoda: "  + metoda.getMethodString());
                       element.addNewContent(metoda);
                       addToBoxes(metoda.getMethodString());
                   } else if (t.equals("Attribute")) {
                       Visibility visibility;
                       if (visibilityBox.getSelectedItem().equals("Private"))
                           visibility = Visibility.PRIVATE;
                       else if (visibilityBox.getSelectedItem().equals("Public"))
                           visibility = Visibility.PUBLIC;
                       else
                           visibility = Visibility.PROTECTED;
                       Attribute attribute = new Attribute(newNameField.getText(), newTypeField.getText(), visibility);
                       System.out.println("Napravio se atribut: "  + attribute.getAttributeString());
                       element.addNewContent(attribute);
                       addToBoxes(attribute.getAttributeString());
                   } else if (t.equals("EnumType")) {
                       EnumType enumerable = new EnumType(newNameField.getText());
                       System.out.println("Napravio se enumType: "  + enumerable.getEnumerableString());
                       element.addNewContent(enumerable);
                       addToBoxes(enumerable.getEnumerableString());
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
                if (indexContenta!=-1) {
                    removeFromBoxes(indexContenta);
                    element.deleteContent(indexContenta);
                }

            }
        });
        changeContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexContenta = contentListBox2.getSelectedIndex();
                if (indexContenta!=-1) {
                    if (element.getContent().get(indexContenta) instanceof Attribute ||
                            element.getContent().get(indexContenta) instanceof Method) {

                        Visibility visibility;
                        if (changeVisibilityBox.getSelectedItem().equals("Private"))
                            visibility = Visibility.PRIVATE;
                        else if (changeVisibilityBox.getSelectedItem().equals("Private"))
                            visibility = Visibility.PUBLIC;
                        else
                            visibility = Visibility.PROTECTED;
                        String newName = changeNameField.getText();
                        String newType = changeTypeField.getText();
                        element.changeContent(indexContenta,newName,newType,visibility);
                        populateBox(contentList1, element.getContent());
                        populateBox(contentList2, element.getContent());
                        refreshComboBoxes(contentList1);
                    } else if (element.getContent().get(indexContenta) instanceof EnumType) {
                        String newName = changeNameField.getText();
                        element.changeContent(indexContenta, newName.toUpperCase(), null, null);
                        populateBox(contentList1, element.getContent());
                        populateBox(contentList2, element.getContent());
                        refreshComboBoxes(contentList1);
                    }

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
        if (element instanceof Klasa || element instanceof Interface) {
            add(changeTypeField);
            add(changeVisibilityBox);
        }
        add(changeContentButton);
    }

    public void populateBox(List<String> content, List<ClassContent> classContents) {
        content.clear();
        for (ClassContent classContent: classContents) {
            if (classContent instanceof Attribute)
                content.add(((Attribute) classContent).getAttributeString());
            if (classContent instanceof Method)
                content.add(((Method) classContent).getMethodString());
            if (classContent instanceof EnumType)
                content.add(((EnumType) classContent).getEnumerableString());
        }
    }

    public void refreshComboBoxes(List<String> content) {
        contentListBox1.removeAllItems();
        contentListBox2.removeAllItems();
        for (String s: content) {
            contentListBox1.addItem(s);
            contentListBox2.addItem(s);
        }
    }

    public void addToBoxes(String content) {
        contentListBox1.addItem(content);
        contentListBox2.addItem(content);
    }

    public void removeFromBoxes(int index) {
        contentListBox1.removeItemAt(index);
        contentListBox2.removeItemAt(index);
    }

}
