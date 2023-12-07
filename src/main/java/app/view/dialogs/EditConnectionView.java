package app.view.dialogs;

import app.model.classcontent.Attribute;
import app.model.classcontent.ClassContent;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditConnectionView extends JFrame {
    private JTextField nameTextField;
    private JComboBox contentListBox1;
    private JComboBox contentListBox2;
    private DiagramView diagramView;
    public EditConnectionView(Connection element, DiagramView diagramView) {
        this.diagramView = diagramView;
        setTitle("Edit Connection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init(element);
        setLayout(new FlowLayout());
        setSize(600, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void init(Connection element) {
        JLabel changeName = new JLabel("Change Connection Name:");
        nameTextField = new JTextField();
        nameTextField.setText(element.getName());

        JLabel container = new JLabel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        container.add(changeName);
        container.add(nameTextField);

        JButton changeNameButton = new JButton("Change Name");

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

        String cardinalityTypes[] = new String[4];
        cardinalityTypes[0] = "ZERO OR ONE";
        cardinalityTypes[1] = "ONE ONLY";
        cardinalityTypes[2] = "ZERO OR MORE";
        cardinalityTypes[3] = "ONE OR MORE";
        String visibilities[] = new String[2];
        visibilities[0] = "Private";
        visibilities[1] = "Public";

        JLabel createNewCardinalitiesLbl = new JLabel("Create a new Cardinality");

        JComboBox cardinalityBox = new JComboBox<>(cardinalityTypes);

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setName(nameTextField.getText());
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            }
        });

        nameTextField.setPreferredSize(new Dimension(200,20));

        add(changeName);
        add(empty_line1);
        add(nameTextField);
        add(changeNameButton);
        add(empty_line2);
        add(createNewCardinalitiesLbl);
        add(cardinalityBox);
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

    public void refreshComboBoxes(String[] content) {
        contentListBox1.removeAllItems();
        contentListBox2.removeAllItems();
        for (String s: content) {
            contentListBox1.addItem(s);
            contentListBox2.addItem(s);
        }
    }

}
