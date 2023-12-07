package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ClassPainter;
import app.view.painters.ElementPainter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MoveState implements State {
    private boolean movingElements = false;
    private boolean movingOneElement = false;
    private Interclass selectedElement;
    //private List<Interclass> selectedElements;
    private ElementPainter selectedPainter;
    private Point startPoint;
    private int lastPointX;
    private int lastPointY;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutne koordinate: (" + x + "," + y + ")");
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                if (elementPainter.getElement().isSelected()) {
                    movingElements = true;
                    movingOneElement = false;
                    System.out.println("Jeste selektovan");
                }
                else {
                    movingOneElement = true;
                    movingElements = false;
                    System.out.println("Nije selektovan");
                }

                break;
            }
        }
        if (movingOneElement) {
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    //movingElements = true;
                    selectedElement = (Interclass) elementPainter.getElement();
                    selectedPainter = elementPainter;
                    System.out.println("Pomeram element: " + elementPainter.getElement().getName());
                }
            }
        }

        startPoint = diagramView.getAbsolutePoint(x, y);
        lastPointX = (int) diagramView.getAbsolutePoint(x,y).getX();
        lastPointY = (int) diagramView.getAbsolutePoint(x,y).getY();
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (movingOneElement) {
            selectedElement.setPosition(diagramView.getAbsolutePoint(x, y));
        } else if (movingElements) {
            int deltaX = (int) (diagramView.getAbsolutePoint(x, y).getX() - lastPointX);
            int deltaY = (int) (diagramView.getAbsolutePoint(x, y).getY() - lastPointY);

            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass) {
                    if (elementPainter.getElement().isSelected()) {

                        int newX = (int) (((Interclass) elementPainter.getElement()).getPosition().getX() + deltaX);
                        int newY = (int) (((Interclass) elementPainter.getElement()).getPosition().getY() + deltaY);
                        ((Interclass) elementPainter.getElement()).setPosition(diagramView.getAbsolutePoint(newX, newY));
                    }
                }
            }
            lastPointX = (int) diagramView.getAbsolutePoint(x, y).getX();
            lastPointY = (int) diagramView.getAbsolutePoint(x, y).getY();
        }
        else {
            Point endPoint = diagramView.getAbsolutePoint(x, y);
            double offsetX = - (startPoint.x - endPoint.getX());
            double offsetY = - (startPoint.y - endPoint.getY());

            double xNew = (diagramView.getDeltaX() + offsetX*diagramView.getScale());
            double yNew = (diagramView.getDeltaY() + offsetY*diagramView.getScale());

            diagramView.setDeltaX((int) xNew);
            diagramView.setDeltaY((int) yNew);

            diagramView.repaint();
        }

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u MoveState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
        if (movingOneElement) {
            selectedElement.setPosition(diagramView.getAbsolutePoint(x, y));
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (!selectedElement.getId().equals(elementPainter.getElement().getId())) {
                    if (elementPainter.getElement() instanceof Interclass && elementPainter.getShape().intersects((Rectangle2D) selectedPainter.getShape())) {
                        selectedElement.setPosition(startPoint);
                    }
                }
            }


        }
        selectedElement = null;
        movingElements = false;
        movingOneElement = false;
    }
}
