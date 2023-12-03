package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;

import java.awt.*;

public class EditState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {

        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter.elementAt(elementPainter.getElement(), new Point(x,y))) {
                //System.out.println("Element na toj poziciji postoji");
                System.out.println("Element na toj poziciji je: " + (Klasa)elementPainter.getElement());
                break;
            }
        }


    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u EditState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u EditState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
