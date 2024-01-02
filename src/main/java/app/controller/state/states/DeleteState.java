package app.controller.state.states;

import app.controller.state.State;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.DiagramElement;
import app.model.diagcomposite.Interclass;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.*;

import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State {

    int indexLinka = -1;
    private boolean deletingOneElement = false;
    private boolean deletingElements = false;
    private boolean isConnection = false;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Connection && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                isConnection = true;
                indexLinka = diagramView.getElementPainters().indexOf(elementPainter);
                System.out.println("Jeste konekcija");
                break;
            }
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                if (elementPainter.getElement().isSelected()) {
                    deletingElements = true;
                    deletingOneElement = false;
                } else {
                    deletingOneElement = true;
                    deletingElements = false;
                }

                break;
            }
        }
        if (deletingOneElement) {
            int indexZaBrisanje = -1;
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    indexZaBrisanje = diagramView.getElementPainters().indexOf(elementPainter);
                    break;
                }

            }
            if (indexZaBrisanje != -1) {
                if (!isConnection) {
                    ArrayList<ElementPainter> toDelete = new ArrayList<>();
                    ArrayList<DiagramElement> toDeleteChild = new ArrayList<>();

                    for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                        if (elementPainter.getElement() instanceof Connection) {
                            ElementPainter link = elementPainter;

                            if (((Connection) link.getElement()).getFromInterclass().getName().equals(diagramView.getElementPainters().get(indexZaBrisanje).getElement().getName())
                                    || ((Connection) link.getElement()).getToInterclass().getName().equals(diagramView.getElementPainters().get(indexZaBrisanje).getElement().getName())) {
                                if (!toDelete.contains(elementPainter))
                                    toDelete.add(elementPainter);
                                if (!toDeleteChild.contains(elementPainter))
                                    toDeleteChild.add(elementPainter.getElement());
                            }
                        }
                    }
                    toDelete.add(diagramView.getElementPainters().get(indexZaBrisanje));
                    toDeleteChild.add(diagramView.getElementPainters().get(indexZaBrisanje).getElement());
                    for (ElementPainter elementPainter : toDelete) {
                        MainFrame.getInstance().getClassyTree().deleteChildFromTree(elementPainter.getElement().getMyNodeMutable());
                    }

                    diagramView.getElementPainters().removeAll(toDelete);

                    diagramView.getDiagramNode().removeChildren(toDeleteChild);
                    MainFrame.getInstance().getCurrentProject().setChanged(true);
                }

            }
        } else if (deletingElements) {
            List<Integer> listaIndexa = new ArrayList<>();
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass && elementPainter.getElement().isSelected()) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    listaIndexa.add(diagramView.getElementPainters().indexOf(elementPainter));
                }

            }
            ArrayList<ElementPainter> toDelete = new ArrayList<>();
            ArrayList<DiagramElement> toDeleteChild = new ArrayList<>();
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Connection) {
                    ElementPainter link = elementPainter;

                    for (int index : listaIndexa) {
                        if (((Connection) link.getElement()).getFromInterclass().getName().equals(diagramView.getElementPainters().get(index).getElement().getName())
                                || ((Connection) link.getElement()).getToInterclass().getName().equals(diagramView.getElementPainters().get(index).getElement().getName())) {
                            if (!toDelete.contains(elementPainter))
                                toDelete.add(elementPainter);
                            if (!toDeleteChild.contains(elementPainter))
                                toDeleteChild.add(elementPainter.getElement());
                        }
                    }

                }
            }
            for (int indexZaBrisanje : listaIndexa) {
                toDelete.add(diagramView.getElementPainters().get(indexZaBrisanje));
                toDeleteChild.add(diagramView.getElementPainters().get(indexZaBrisanje).getElement());
            }
            for (ElementPainter elementPainter : toDelete) {
                MainFrame.getInstance().getClassyTree().deleteChildFromTree(elementPainter.getElement().getMyNodeMutable());
            }
            diagramView.getElementPainters().removeAll(toDelete);
            diagramView.getDiagramNode().removeChildren(toDeleteChild);
            MainFrame.getInstance().getCurrentProject().setChanged(true);
        } else if (isConnection) {
            diagramView.getDiagramNode().removeChild(diagramView.getElementPainters().get(indexLinka).getElement());
            MainFrame.getInstance().getClassyTree().deleteChildFromTree(diagramView.getElementPainters().get(indexLinka).getElement().getMyNodeMutable());
            diagramView.getElementPainters().remove(diagramView.getElementPainters().get(indexLinka));
            MainFrame.getInstance().getCurrentProject().setChanged(true);

        }
        deletingOneElement = false;
        deletingElements = false;
        isConnection = false;

    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
      //  System.out.println("Trenutno si u DeleteState i povukao  si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
     //   System.out.println("Trenutno si u DeleteState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
