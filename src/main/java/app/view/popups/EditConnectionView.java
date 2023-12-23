package app.view.popups;

import app.model.classcontent.*;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.editcomponents.Cardinality;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.editcomponents.CardinalityType;
import app.model.diagimplementation.connection.editcomponents.Dependencies;
import app.model.diagimplementation.connection.editcomponents.DependencyType;
import app.view.mainframe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditConnectionView extends JFrame {
    private JTextField nameTextField;
    private JComboBox currentCardinalities = new JComboBox<>();
    private JComboBox currentDependencies = new JComboBox<>();
    public EditConnectionView(Connection element) {
        setTitle("Edit Connection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init(element);
        setLayout(new FlowLayout());
        setSize(700, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/settings-icon2.png")).getImage());
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
        JButton addCardinalityButton = new JButton("Add Cardinality");
        JButton addDependencyButton = new JButton("Add Dependency");

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
        String visibilities[] = new String[3];
        visibilities[0] = "Private";
        visibilities[1] = "Public";
        visibilities[2] = "Protected";
        String interclasses[] = new String[2];
        interclasses[0] = element.getFromInterclass().getName();
        interclasses[1] = element.getToInterclass().getName();

        String[] dependencyTypes = new String[5];
        dependencyTypes[0] = "INSTANTATE";
        dependencyTypes[1] = "CALL";
        dependencyTypes[2] = "USE";
        dependencyTypes[3] = "INCLUDE";
        dependencyTypes[4] = "EXTEND";

        JLabel createNewCardinalitiesLbl = new JLabel("Create a new Cardinality: ");

        ArrayList<String> attributes1 = new ArrayList<>();

        for (ClassContent content: element.getFromInterclass().getContent()) {
            if (content instanceof Attribute) {
                attributes1.add(((Attribute) content).attributeString());
            }
        }

        ArrayList<String> attributes2 = new ArrayList<>();

        for (ClassContent content: element.getToInterclass().getContent()) {
            if (content instanceof Attribute) {
                attributes2.add(((Attribute) content).attributeString());
            }
        }

        ArrayList<String> toInterclassContentList = new ArrayList<>();

        for (ClassContent content: element.getToInterclass().getContent()) {
            if (content instanceof Attribute) {
                toInterclassContentList.add(((Attribute) content).attributeString());
            } else if (content instanceof Method) {
                toInterclassContentList.add(((Method) content).methodString());
            }
        }

        JComboBox cardinalityBox = new JComboBox<>(cardinalityTypes);
        JComboBox dependencyTypeBox = new JComboBox(dependencyTypes);


        JLabel chooseCardinalityTypeLbl = new JLabel("Pick a cardinality type: ");
        JLabel classThatInstantiates = new JLabel("Class that instantiates: ");
        JLabel savedAttributeLbl = new JLabel("Attribute created: ");
        JLabel currentCardinalitiesLbl = new JLabel("Current cardinalities: ");

        JTextField newNameField = new JTextField();
        newNameField.setText("Enter name here");
        JComboBox visibilityBox = new JComboBox(visibilities);

        newNameField.setPreferredSize(new Dimension(100,20));

        JLabel currentDependenciesLbl = new JLabel("Current dependencies: ");
        JLabel createDependenciesLbl = new JLabel("Create Dependency: ");
        JLabel instanceClassLbl = new JLabel();
        JLabel connectsLbl = new JLabel("Connects: " + element.getFromInterclass().getName() + " and " + element.getToInterclass());

        ArrayList<String> cardinalities = new ArrayList<>();
        ArrayList<String> dependenciesList  = new ArrayList<>();
        JButton deleteDependencyBttn = new JButton("Delete Selected Dependency");
        JButton deleteCardinalityBttn = new JButton("Delete Selected Cardinality");

        if (element instanceof Aggregation) {
            for (Cardinality cardinality: ((Aggregation) element).getCardinalitiesList()) {
                cardinalities.add(cardinality.toString());
            }
        }
        if (element instanceof Composition) {
            for (Cardinality cardinality: (( Composition) element).getCardinalitiesList()) {
                cardinalities.add(cardinality.toString());
            }
        }
        if (element instanceof Dependency) {
            for (Dependencies dependency: (( Dependency) element).getDependencies()) {
                dependenciesList.add(dependency.toString());
            }
        }

        currentDependencies.setModel(new DefaultComboBoxModel(dependenciesList.toArray()));
        currentCardinalities.setModel(new DefaultComboBoxModel(cardinalities.toArray()));


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
                if (visibilityBox.getSelectedIndex()==0)
                    visibility = Visibility.PRIVATE;
                else if (visibilityBox.getSelectedIndex()==1)
                    visibility = Visibility.PUBLIC;
                else
                    visibility = Visibility.PROTECTED;
                int kardinalnostIndex = cardinalityBox.getSelectedIndex();
                CardinalityType newCardinalityType = null;
                switch (kardinalnostIndex) {
                    case 0:
                        newCardinalityType = CardinalityType.ZERO_OR_ONE;
                        break;
                    case 1:
                        newCardinalityType = CardinalityType.ONE_ONLY;
                        break;
                    case 2:
                        newCardinalityType = CardinalityType.ZERO_OR_MORE;
                        break;
                    case 3:
                        newCardinalityType = CardinalityType.ONE_OR_MORE;
                        break;
                }
                Interclass objectInstantiated = element.getToInterclass();
                String attributeType;
                if (newCardinalityType.equals(CardinalityType.ONE_ONLY))
                    attributeType = objectInstantiated.getName();
                else
                    attributeType = "List<" + objectInstantiated.getName() + ">";
                Attribute newAttribute = new Attribute(newNameField.getText(), attributeType, visibility);
                System.out.println(newAttribute.attributeString());
                if (element instanceof Aggregation) {
                    Cardinality cardinality = new Cardinality(newCardinalityType, element.getFromInterclass(), objectInstantiated, newAttribute);
                    ((Aggregation) element).getCardinalitiesList().add(cardinality);

                    cardinalities.add(cardinality.toString());
                    refreshCurrentCardinalities(cardinalities);
                }
                if (element instanceof Composition) {
                    Cardinality cardinality = new Cardinality(newCardinalityType, element.getFromInterclass(), objectInstantiated, newAttribute);
                    ((Composition) element).getCardinalitiesList().add(cardinality);
                    cardinalities.add(cardinality.toString());
                    refreshCurrentCardinalities(cardinalities);
                }


            }
        });



        addDependencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DependencyType dependencyType = null;
                int indexSelectedDependencyType = dependencyTypeBox.getSelectedIndex();
                switch (indexSelectedDependencyType) {
                    case 0:
                        dependencyType = DependencyType.INSTANTIATE;
                        break;
                    case 1:
                        dependencyType = DependencyType.CALL;
                        break;
                    case 2:
                        dependencyType = DependencyType.USE;
                        break;
                    case 3:
                        dependencyType = DependencyType.INCLUDE;
                        break;
                    case 4:
                        dependencyType = DependencyType.EXTEND;
                        break;
                }
                Dependencies dependencyToAdd = new Dependencies(element.getFromInterclass(), element.getToInterclass(), dependencyType);
                ((Dependency) element).getDependencies().add(dependencyToAdd);
                dependenciesList.add(dependencyToAdd.toString());
                refreshCurrentDependencies(dependenciesList);
            }
        });

        deleteCardinalityBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexForDeletion = currentCardinalities.getSelectedIndex();
                if (indexForDeletion!=-1) {
                    if (element instanceof Aggregation) {
                        ((Aggregation) element).getCardinalitiesList().remove(indexForDeletion);
                        cardinalities.remove(indexForDeletion);
                    }
                    else if (element instanceof Composition) {
                        ((Composition) element).getCardinalitiesList().remove(indexForDeletion);
                        cardinalities.remove(indexForDeletion);
                    }
                    refreshCurrentCardinalities(cardinalities);
                }

            }
        });

        deleteDependencyBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexForDeletion = currentDependencies.getSelectedIndex();
                if (indexForDeletion!=-1) {
                    if (element instanceof Dependency) {
                        ((Dependency) element).getDependencies().remove(indexForDeletion);
                        dependenciesList.remove(indexForDeletion);
                    }
                    refreshCurrentDependencies(dependenciesList);
                }
            }
        });

        nameTextField.setPreferredSize(new Dimension(200,20));
        instanceClassLbl.setText(element.getFromInterclass().getName());
        JLabel addDependencyLbl1 = new JLabel("Interclass " + element.getFromInterclass().getName() + " is dependant on " + element.getToInterclass().getName() + " via: ");
        JLabel generalizationLbl = new JLabel(element.getToInterclass().getName() + " is Parent, and " + element.getFromInterclass().getName() + " is it's Child. ");

        add(changeName);
        add(empty_line1);
        add(nameTextField);
        add(changeNameButton);
        add(empty_line2);
        add(connectsLbl);
        add(empty_line5);
        if (element instanceof Composition || element instanceof Aggregation) {
            add(currentCardinalitiesLbl);
            add(currentCardinalities);
            add(deleteCardinalityBttn);
            add(empty_line7);
            add(createNewCardinalitiesLbl);
            add(empty_line3);
            add(chooseCardinalityTypeLbl);
            add(cardinalityBox);
            add(empty_line4);
            add(classThatInstantiates);
            add(instanceClassLbl);
            add(empty_line6);
            add(savedAttributeLbl);
            add(newNameField);
            add(visibilityBox);
            add(addCardinalityButton);
        } else if (element instanceof Dependency) {
            add(currentDependenciesLbl);
            add(currentDependencies);
            add(deleteDependencyBttn);
            add(empty_line7);
            add(createDependenciesLbl);
            add(empty_line3);
            add(addDependencyLbl1);
            add(dependencyTypeBox);
            add(addDependencyButton);
        } else
            add(generalizationLbl);

    }

    public void refreshCurrentCardinalities(ArrayList<String> content) {
        currentCardinalities.removeAllItems();
        for (String s: content) {
            currentCardinalities.addItem(s);
        }
    }

    public void refreshCurrentDependencies(ArrayList<String> content) {
        currentDependencies.removeAllItems();
        for (String s: content) {
            currentDependencies.addItem(s);
        }
    }


}
