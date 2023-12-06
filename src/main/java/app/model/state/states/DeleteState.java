package app.model.state.states;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.DiagramElement;
import app.model.diagcomposite.Interclass;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.*;

import java.util.ArrayList;

public class DeleteState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        int indexZaBrisanje = -1;
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {

            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                indexZaBrisanje = diagramView.getElementPainters().indexOf(elementPainter);
                break;
            }

        }
        if (indexZaBrisanje!=-1) {
            ArrayList<ElementPainter> toDelete = new ArrayList<>();
            ArrayList<DiagramElement> toDeleteChild = new ArrayList<>();

            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter instanceof AggregationPainter) {
                    AggregationPainter link = (AggregationPainter) elementPainter;
                    if (((Connection)link.getElement()).getFromInterclass().getName().equals(diagramView.getElementPainters().get(indexZaBrisanje).getElement().getName())
                        || ((Connection)link.getElement()).getToInterclass().getName().equals(diagramView.getElementPainters().get(indexZaBrisanje).getElement().getName())){
                        toDelete.add(elementPainter);
                        toDeleteChild.add(elementPainter.getElement());
                    }
                }
            }
            toDelete.add(diagramView.getElementPainters().get(indexZaBrisanje));
            toDeleteChild.add(diagramView.getElementPainters().get(indexZaBrisanje).getElement());
            diagramView.getElementPainters().removeAll(toDelete);
            diagramView.getDiagramNode().removeChildren(toDeleteChild);
        }

    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u DeleteState i povukao  si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u DeleteState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
