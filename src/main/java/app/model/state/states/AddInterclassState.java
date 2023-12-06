package app.model.state.states;

import app.model.classcontent.Attribute;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.ClassPainter;
import app.view.painters.ElementPainter;
import app.view.painters.EnumPainter;
import app.view.painters.InterfacePainter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AddInterclassState implements State {
    private boolean elementExists = false;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter elementPainter:  diagramView.getElementPainters()) {
            if (elementPainter.elementAt(elementPainter.getElement(),diagramView.getAbsolutePoint(x, y))) {
                elementExists = true;
                System.out.println("Vec postoji element na ovoj tacki");
            }
        }
        if (!elementExists) {
            String[] tipInterklase = {"Klasa", "Interfejs", "Enum"};
            int odg = JOptionPane.showOptionDialog(null, "Choose the Interclass Type",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipInterklase, tipInterklase[0]);
            System.out.println("Izabrana je opcija "  + odg);
            if (odg==0) {
                Klasa klasa = new Klasa(diagramView.getAbsolutePoint(x, y), diagramView.getDiagramNode(), "Klasa" + new Random().nextInt(100), 2);

                MainFrame.getInstance().setChildToCreateType("klasa");
                System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
                klasa.getContent().add(new Attribute("height", "double", Visibility.PRIVATE));
                klasa.getContent().add(new Attribute("height2", "double", Visibility.PRIVATE));
                klasa.getContent().add(new Attribute("height3", "double", Visibility.PRIVATE));
                klasa.getContent().add(new Attribute("height4", "double", Visibility.PRIVATE));
                klasa.getContent().add(new Attribute("height5", "double", Visibility.PRIVATE));
                klasa.getContent().add(new Method("calculateHeight", "double", Visibility.PUBLIC));

                klasa.setCurrentColor(new Color(238,230,194));
                klasa.setInitialColor(new Color(238,230,194));
                MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), klasa);
                klasa.addSubscriber(diagramView);
                ClassPainter classPainter = new ClassPainter(klasa);
                classPainter.setElement(klasa);

                diagramView.getElementPainters().add(classPainter);
                System.out.println("Ime klase je: " + klasa);
            } else if (odg==1) {
                Interface interfejs = new Interface(diagramView.getAbsolutePoint(x, y), diagramView.getDiagramNode(), "Interface" + new Random().nextInt(100), 2);
                interfejs.getContent().add(new Method("funkcija1", "int", Visibility.PUBLIC));
                interfejs.getContent().add(new Method("funkcija2", "double", Visibility.PUBLIC));
                interfejs.getContent().add(new Method("funkcija3", "String", Visibility.PUBLIC));
                interfejs.getContent().add(new Method("funkcija4", "Vehicle", Visibility.PUBLIC));
                interfejs.addSubscriber(diagramView);
                InterfacePainter interfejsPainter = new InterfacePainter(interfejs);
                interfejsPainter.setElement(interfejs);

                MainFrame.getInstance().setChildToCreateType("interface");
                System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
                diagramView.getElementPainters().add(interfejsPainter);
                MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), interfejs);
                System.out.println("Ime interfejsa je: " + interfejs);
            } else if (odg==2) {
                EnumComp enumComp = new EnumComp(diagramView.getAbsolutePoint(x, y), diagramView.getDiagramNode(), "Enum" + new Random().nextInt(100), 2);
                enumComp.getContent().add(new EnumType("car"));
                enumComp.getContent().add(new EnumType("plane"));
                enumComp.getContent().add(new EnumType("boat"));
                enumComp.addSubscriber(diagramView);
                EnumPainter enumPainter = new EnumPainter(enumComp);
                MainFrame.getInstance().setChildToCreateType("enum");
                System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
                enumPainter.setElement(enumComp);
                diagramView.getElementPainters().add(enumPainter);
                MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), enumComp);
                System.out.println("Ime enuma je: " + enumComp);
            }
        }

        elementExists = false;
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u AddInterClassState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u AddInterClassState i pustio si na tacki: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
