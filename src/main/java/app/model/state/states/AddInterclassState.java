package app.model.state.states;

import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.ClassPainter;
import app.view.painters.EnumPainter;
import app.view.painters.InterfacePainter;

import javax.swing.*;
import java.util.Random;

public class AddInterclassState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
         String[] tipInterklase = {"Klasa", "Interfejs", "Enum"};
        int odg = JOptionPane.showOptionDialog(null, "Choose the Interclass Type",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipInterklase, tipInterklase[0]);
         System.out.println("Izabrana je opcija "  + odg);
         if (odg==0) {
             Klasa klasa = new Klasa(diagramView.getAbsolutePoint(x, y), "Klasa" + new Random().nextInt(100), 2);

             MainFrame.getInstance().setChildToCreateType("klasa");
             System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
            // diagramView.getDiagramNode().addChild(klasa);
            // MyNodeMutable parentToAdd = new MyNodeMutable(diagramView.getDiagramNode());
             MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), klasa);
             ClassPainter classPainter = new ClassPainter(klasa);
             classPainter.setElement(klasa);

             diagramView.getElementPainters().add(classPainter);
             System.out.println("Ime klase je: " + klasa);
         } else if (odg==1) {
             Interface interfejs = new Interface(diagramView.getAbsolutePoint(x, y), "Interface" + new Random().nextInt(100), 2);

             InterfacePainter interfejsPainter = new InterfacePainter(interfejs);
             interfejsPainter.setElement(interfejs);

             MainFrame.getInstance().setChildToCreateType("interface");
             System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
             diagramView.getElementPainters().add(interfejsPainter);
            // diagramView.getDiagramNode().addChild(interfejs);
             MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), interfejs);
             System.out.println("Ime interfejsa je: " + interfejs);
         } else if (odg==2) {
             EnumComp enumComp = new EnumComp(diagramView.getAbsolutePoint(x, y), "Enum" + new Random().nextInt(100), 2);

             EnumPainter enumPainter = new EnumPainter(enumComp);
             MainFrame.getInstance().setChildToCreateType("enum");
             System.out.println("Setovan childToCreateType na: " + MainFrame.getInstance().getChildToCreateType());
             enumPainter.setElement(enumComp);
             diagramView.getElementPainters().add(enumPainter);
             //diagramView.getDiagramNode().addChild(enumComp);
             MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), enumComp);
             System.out.println("Ime enuma je: " + enumComp);
         }



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
