package app.model.state.states;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.state.State;
import app.view.dialogs.EditConnectionView;
import app.view.dialogs.EditInterclassView;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;

public class EditState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {

        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y)) &&
                    elementPainter.getElement() instanceof Connection) {
                System.out.println("Linija ovde je: " + elementPainter.getElement().getName());
                new EditConnectionView((Connection) elementPainter.getElement());
            }
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x,y))) {
                new EditInterclassView((Interclass) elementPainter.getElement());
                System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
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
