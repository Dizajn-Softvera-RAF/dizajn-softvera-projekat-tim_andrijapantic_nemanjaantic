package app.model.state.states;

import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ClassPainter;

import java.awt.*;

public class AddInterclassState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {

         Klasa klasa = new Klasa();
         klasa.setPosition(new Point(x,y));
         klasa.setStroke(3);
         klasa.setName("Ime ime");

         ClassPainter classPainter = new ClassPainter(klasa);
         diagramView.getElementPainters().add(classPainter);
         diagramView.getDiagramNode().addChild(klasa);


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
