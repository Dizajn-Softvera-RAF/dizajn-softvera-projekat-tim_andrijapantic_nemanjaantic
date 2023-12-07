package app.model.state.states;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.DiagramElement;
import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.*;

import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State {

    private boolean deletingOneElement = false;
    private boolean deletingElements = false;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                if (elementPainter.getElement().isSelected()) {
                    deletingElements = true;
                    deletingOneElement = false;
                    System.out.println("Jeste selektovan");
                }
                else {
                    deletingOneElement = true;
                    deletingElements = false;
                    System.out.println("Nije selektovan");
                }

                break;
            }
        }
        if (deletingOneElement) {
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
                    if (elementPainter.getElement() instanceof Connection) {
                        ElementPainter link = null;
                        if (elementPainter.getElement() instanceof Aggregation) {
                            link = (AggregationPainter) elementPainter;
                        } else if (elementPainter.getElement() instanceof Composition) {
                            link = (CompositionPainter) elementPainter;
                        }

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
        } else if (deletingElements) {
            List<Integer> listaIndexa = new ArrayList<>();
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass && elementPainter.getElement().isSelected()) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    listaIndexa.add(diagramView.getElementPainters().indexOf(elementPainter));
                }

            }
            ArrayList<ElementPainter> toDelete = new ArrayList<>();
            ArrayList<DiagramElement> toDeleteChild = new ArrayList<>();
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Connection) {
                    ElementPainter link = null;
                    if (elementPainter.getElement() instanceof Aggregation) {
                        link = (AggregationPainter) elementPainter;
                    } else if (elementPainter.getElement() instanceof Composition) {
                        link = (CompositionPainter) elementPainter;
                    }

                    for (int index: listaIndexa) {
                        if (((Connection)link.getElement()).getFromInterclass().getName().equals(diagramView.getElementPainters().get(index).getElement().getName())
                                || ((Connection)link.getElement()).getToInterclass().getName().equals(diagramView.getElementPainters().get(index).getElement().getName())){
                            toDelete.add(elementPainter);
                            toDeleteChild.add(elementPainter.getElement());
                        }
                    }

                }
            }
            for (int indexZaBrisanje: listaIndexa) {
                toDelete.add(diagramView.getElementPainters().get(indexZaBrisanje));
                toDeleteChild.add(diagramView.getElementPainters().get(indexZaBrisanje).getElement());
            }
            diagramView.getElementPainters().removeAll(toDelete);
            diagramView.getDiagramNode().removeChildren(toDeleteChild);
        }
        deletingOneElement = false;
        deletingElements = false;

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
