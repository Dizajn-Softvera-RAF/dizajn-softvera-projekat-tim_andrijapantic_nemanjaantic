package app.model.state.states;

import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ClassPainter;
import app.view.painters.EnumPainter;
import app.view.painters.InterfacePainter;

import javax.swing.*;
import java.awt.*;

public class AddInterclassState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
         String[] tipInterklase = {"Klasa", "Interfejs", "Enum"};
        int odg = JOptionPane.showOptionDialog(null, "Choose the Interclass Type",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipInterklase, tipInterklase[0]);
         System.out.println("Izabrana je opcija "  + odg);
         if (odg==0) {
             Klasa klasa = new Klasa();
             klasa.setPosition(new Point(x,y));
             klasa.setStroke(2);
             klasa.setName("Klasa");

             ClassPainter classPainter = new ClassPainter(klasa);
             classPainter.setElement(klasa);
             diagramView.getElementPainters().add(classPainter);
             diagramView.getDiagramNode().addChild(klasa);
         } else if (odg==1) {
             Interface interfejs = new Interface();
             interfejs.setPosition(new Point(x,y));
             interfejs.setStroke(2);
             interfejs.setName("Interface");

             InterfacePainter interfejsPainter = new InterfacePainter(interfejs);
             interfejsPainter.setElement(interfejs);
             diagramView.getElementPainters().add(interfejsPainter);
             diagramView.getDiagramNode().addChild(interfejs);
         } else if (odg==2) {
             EnumComp enumComp = new EnumComp();
             enumComp.setPosition(new Point(x,y));
             enumComp.setStroke(2);
             enumComp.setName("Enum");

             EnumPainter enumPainter = new EnumPainter(enumComp);
             enumPainter.setElement(enumComp);
             diagramView.getElementPainters().add(enumPainter);
             diagramView.getDiagramNode().addChild(enumComp);
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
