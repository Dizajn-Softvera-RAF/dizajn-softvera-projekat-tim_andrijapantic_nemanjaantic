package app.view.dialogs;

import app.model.classcontent.*;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Cardinalities;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditConnectionView extends JFrame {
    private JTextField nameTextField;
    private DiagramView diagramView;
    private JComboBox currentCardinalities = new JComboBox<>();
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
        JButton addCardinalityButton = new JButton("Add cardinality");

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
        String interclasses[] = new String[2];
        interclasses[0] = element.getFromInterclass().getName();
        interclasses[1] = element.getToInterclass().getName();

        JLabel createNewCardinalitiesLbl = new JLabel("Create a new Cardinality: ");

        ArrayList<String> attributes1 = new ArrayList<>();

        for (ClassContent content: element.getFromInterclass().getContent()) {
            if (content instanceof Attribute) {
                attributes1.add(((Attribute) content).getAttributeString());
            }
        }

        ArrayList<String> attributes2 = new ArrayList<>();

        for (ClassContent content: element.getToInterclass().getContent()) {
            if (content instanceof Attribute) {
                attributes2.add(((Attribute) content).getAttributeString());
            }
        }

        JComboBox cardinalityBox = new JComboBox<>(cardinalityTypes);
        JComboBox interclassesBox = new JComboBox<>(interclasses);
        JComboBox attributesBox = new JComboBox<>(attributes2.toArray());

        JLabel chooseCardinalityTypeLbl = new JLabel("Pick a cardinality type: ");
        JLabel chooseAClassLbl = new JLabel("Class that instantiates: ");
        JLabel chooseAttributeLbl = new JLabel("Attribute that gets instantiated from other Interclass: ");
        JLabel savedAttributeLbl = new JLabel("Attribute created: ");
        JLabel currentCardinalitiesLbl = new JLabel("Current cardinalities: ");

        JTextField newNameField = new JTextField();
        newNameField.setText("Enter name here");
        JComboBox visibilityBox = new JComboBox(visibilities);

        newNameField.setPreferredSize(new Dimension(100,20));

        interclassesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex =  interclassesBox.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        attributesBox.setModel(new DefaultComboBoxModel(attributes2.toArray()));
                        break;
                    case 1:
                        attributesBox.setModel(new DefaultComboBoxModel(attributes1.toArray()));
                        break;
                }
            }
        });

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setName(nameTextField.getText());
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            }
        });

        addCardinalityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visibility visibility;
                if (visibilityBox.getSelectedIndex()==1)
                    visibility = Visibility.PUBLIC;
                else
                    visibility = Visibility.PRIVATE;
                int kardinalnostIndex = cardinalityBox.getSelectedIndex();
                Cardinality newCardinality = null;
                switch (kardinalnostIndex) {
                    case 0:
                        newCardinality = Cardinality.ZERO_OR_ONE;
                        break;
                    case 1:
                        newCardinality = Cardinality.ONE_ONLY;
                        break;
                    case 2:
                        newCardinality = Cardinality.ZERO_OR_MORE;
                        break;
                    case 3:
                        newCardinality = Cardinality.ONE_OR_MORE;
                        break;
                }
                Interclass interclassThatContains;
                Interclass interclass2;
                if (interclassesBox.getSelectedIndex()==0) {
                    interclassThatContains = element.getFromInterclass();
                    interclass2 = element.getToInterclass();
                }
                else {
                    interclass2 = element.getFromInterclass();
                    interclassThatContains = element.getToInterclass();
                }
                Attribute attributeUsed = new Attribute(null, null, null);
                for (ClassContent content: interclass2.getContent()) {
                    if (content instanceof Attribute) {
                        if (((Attribute) content).getAttributeString().equals((String) attributesBox.getSelectedItem())) {
                            attributeUsed = (Attribute) content;
                        }
                    }
                }
                Attribute newAttribute = new Attribute(nameTextField.getText(),attributeUsed.getType(),visibility);
                ArrayList<String> cardinalities = new ArrayList<>();
                if (element instanceof Aggregation) {
                    ((Aggregation) element).getCardinalitiesList().add(new Cardinalities(newCardinality, interclassThatContains, attributeUsed, newAttribute));

                    for (Cardinalities cardinality: ((Aggregation) element).getCardinalitiesList()) {
                        cardinalities.add(cardinality.toString());
                    }
                    refreshCurrentCardinalities(cardinalities);
                }
                if (element instanceof Composition) {
                    ((Composition) element).getCardinalitiesList().add(new Cardinalities(newCardinality, interclassThatContains, attributeUsed, newAttribute));
                    for (Cardinalities cardinality: ((Composition) element).getCardinalitiesList()) {
                        cardinalities.add(cardinality.toString());
                    }
                    refreshCurrentCardinalities(cardinalities);
                }


            }
        });

        nameTextField.setPreferredSize(new Dimension(200,20));
        ///JComboBox currentCardinalities = new JComboBox<>();

        add(changeName);
        add(empty_line1);
        add(nameTextField);
        add(changeNameButton);
        add(empty_line2);
        if (element instanceof Composition || element instanceof Aggregation) {
            add(currentCardinalitiesLbl);
            add(currentCardinalities);
            add(empty_line7);
            add(createNewCardinalitiesLbl);
            add(empty_line3);
            add(chooseCardinalityTypeLbl);
            add(cardinalityBox);
            add(empty_line4);
            add(chooseAClassLbl);
            add(interclassesBox);
            add(empty_line5);
            add(chooseAttributeLbl);
            add(attributesBox);
            add(empty_line6);
            add(savedAttributeLbl);
            add(newNameField);
            add(visibilityBox);
            add(addCardinalityButton);
        }

    }

    public void refreshCurrentCardinalities(ArrayList<String> content) {
        currentCardinalities.removeAllItems();
        for (String s: content) {
            currentCardinalities.addItem(s);
        }
    }


}
