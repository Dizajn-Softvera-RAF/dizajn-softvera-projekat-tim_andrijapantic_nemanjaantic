package app.model.state.states;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.DiagramElement;
import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Generalization;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.*;

import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State {

    private boolean deletingOneElement = false;
    private boolean deletingElements = false;
    private boolean isConnection = false;
    int indexLinka = -1;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
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
                if (elementPainter.getElement() instanceof Connection && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    indexZaBrisanje = diagramView.getElementPainters().indexOf(elementPainter);
                    break;
                }
                if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    indexZaBrisanje = diagramView.getElementPainters().indexOf(elementPainter);
                    break;
                }

            }
            if (indexZaBrisanje!=-1) {
                if (!isConnection) {
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
                                if (!toDelete.contains(elementPainter))
                                    toDelete.add(elementPainter);
                                if (!toDeleteChild.contains(elementPainter))
                                    toDeleteChild.add(elementPainter.getElement());
                            }
                        }
                    }
                    toDelete.add(diagramView.getElementPainters().get(indexZaBrisanje));
                    toDeleteChild.add(diagramView.getElementPainters().get(indexZaBrisanje).getElement());
                    for (ElementPainter elementPainter: toDelete) {
                        DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
                        System.out.println("Za element " + elementPainter.getElement().getName() + " parent je: " + elementPainter.getElement().getMyNodeMutable().getParent());
                        model.removeNodeFromParent(elementPainter.getElement().getMyNodeMutable());
                    }

                    diagramView.getElementPainters().removeAll(toDelete);

                    diagramView.getDiagramNode().removeChildren(toDeleteChild);
                }

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
                    ElementPainter link = elementPainter;

                    for (int index: listaIndexa) {
                        if (((Connection)link.getElement()).getFromInterclass().getName().equals(diagramView.getElementPainters().get(index).getElement().getName())
                                || ((Connection)link.getElement()).getToInterclass().getName().equals(diagramView.getElementPainters().get(index).getElement().getName())){
                            if (!toDelete.contains(elementPainter))
                                toDelete.add(elementPainter);
                            if (!toDeleteChild.contains(elementPainter))
                             toDeleteChild.add(elementPainter.getElement());
                        }
                    }

                }
            }
            for (int indexZaBrisanje: listaIndexa) {
                toDelete.add(diagramView.getElementPainters().get(indexZaBrisanje));
                toDeleteChild.add(diagramView.getElementPainters().get(indexZaBrisanje).getElement());
            }
            for (ElementPainter elementPainter: toDelete) {
                DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
                System.out.println("Za element " + elementPainter.getElement().getName() + " parent je: " + elementPainter.getElement().getMyNodeMutable().getParent());
                model.removeNodeFromParent(elementPainter.getElement().getMyNodeMutable());
            }
            diagramView.getElementPainters().removeAll(toDelete);
            diagramView.getDiagramNode().removeChildren(toDeleteChild);
        } else if (isConnection) {
            System.out.println("Ulazim u else za brisanje veze");
            diagramView.getDiagramNode().removeChild(diagramView.getElementPainters().get(indexLinka).getElement());
            DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
            //System.out.println("Za element " + diagramView.getElementPainters().get(indexZaBrisanje) + " parent je: " + elementPainter.getElement().getMyNodeMutable().getParent());
            model.removeNodeFromParent(diagramView.getElementPainters().get(indexLinka).getElement().getMyNodeMutable());
            diagramView.getElementPainters().remove(diagramView.getElementPainters().get(indexLinka));


        }
        deletingOneElement = false;
        deletingElements = false;
        isConnection = false;

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
