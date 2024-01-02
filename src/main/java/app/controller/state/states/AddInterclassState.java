package app.controller.state.states;

import app.controller.state.State;
import app.model.classcontent.Attribute;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.ClassPainter;
import app.view.painters.ElementPainter;
import app.view.painters.EnumPainter;
import app.view.painters.InterfacePainter;

import javax.swing.*;
import java.util.Random;

public class AddInterclassState implements State {
    private boolean elementExists = false;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter elementPainter:  diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(),diagramView.getAbsolutePoint(x, y))) {
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
                klasa.getContent().add(new Attribute("exampleAttribute", "String", Visibility.PUBLIC));
                klasa.getContent().add(new Attribute("exampleAttribute2", "int", Visibility.PUBLIC));
                klasa.getContent().add(new Attribute("exampleAttribute3", "List<String>", Visibility.PRIVATE));
                klasa.getContent().add(new Method("methodExample1", "double", Visibility.PUBLIC));
                klasa.getContent().add(new Method("methodExample2", "void", Visibility.PRIVATE));

                klasa.setCurrentColor(15656642);
                klasa.setInitialColor(15656642);
                klasa.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addExistingChild(diagramView.getMyNodeMutable(), klasa));
                klasa.addSubscriber(diagramView);
                ClassPainter classPainter = new ClassPainter(klasa);
                classPainter.setElement(klasa);

                diagramView.getElementPainters().add(classPainter);
                System.out.println("Ime klase je: " + klasa);
            } else if (odg==1) {
                Interface interfejs = new Interface(diagramView.getAbsolutePoint(x, y), diagramView.getDiagramNode(), "Interface" + new Random().nextInt(100), 2);
                interfejs.getContent().add(new Method("methodExample1", "double", Visibility.PUBLIC));
                interfejs.getContent().add(new Method("methodExample2", "void", Visibility.PUBLIC));
                interfejs.addSubscriber(diagramView);
                interfejs.setInitialColor(12775131);
                interfejs.setCurrentColor(12775131);
                InterfacePainter interfejsPainter = new InterfacePainter(interfejs);
                interfejsPainter.setElement(interfejs);

                MainFrame.getInstance().setChildToCreateType("interface");
                System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
                diagramView.getElementPainters().add(interfejsPainter);
                interfejs.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addExistingChild(diagramView.getMyNodeMutable(), interfejs));
                System.out.println("Ime interfejsa je: " + interfejs);
            } else if (odg==2) {
                EnumComp enumComp = new EnumComp(diagramView.getAbsolutePoint(x, y), diagramView.getDiagramNode(), "Enum" + new Random().nextInt(100), 2);
                enumComp.getContent().add(new EnumType("enum1"));
                enumComp.getContent().add(new EnumType("enum2"));
                enumComp.getContent().add(new EnumType("enum3"));
                enumComp.addSubscriber(diagramView);
                enumComp.setInitialColor(11315931);
                enumComp.setCurrentColor(11315931);
                EnumPainter enumPainter = new EnumPainter(enumComp);
                MainFrame.getInstance().setChildToCreateType("enum");
                System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
                enumPainter.setElement(enumComp);
                diagramView.getElementPainters().add(enumPainter);
                enumComp.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addExistingChild(diagramView.getMyNodeMutable(), enumComp));
                System.out.println("Ime enuma je: " + enumComp);
            }
            MainFrame.getInstance().getCurrentProject().setChanged(true);
        }

        elementExists = false;
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
       // System.out.println("Trenutno si u AddInterClassState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
     //   System.out.println("Trenutno si u AddInterClassState i pustio si na tacki: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
