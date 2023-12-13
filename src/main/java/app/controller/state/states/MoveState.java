package app.controller.state.states;

import app.model.diagcomposite.Interclass;
import app.controller.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MoveState implements State {
    private boolean movingElements = false;
    private boolean movingOneElement = false;
    private int selectedElementIndex = -1;
    private Point startPoint;
    private int lastPointX;
    private int lastPointY;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutne koordinate: (" + x + "," + y + ")");
        int selectedCounter = 0;
        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                if (elementPainter.getElement().isSelected()) {
                    movingElements = true;
                    movingOneElement = false;
                    System.out.println("Jeste selektovan");
                } else {
                    movingOneElement = true;
                    movingElements = false;
                    System.out.println("Nije selektovan");
                }

                break;
            }
        }
        if (movingElements) {
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass && elementPainter.getElement().isSelected())
                    selectedCounter++;
            }
            if (selectedCounter == 1) {
                movingOneElement = true;
                movingElements = false;
            }
        }
        if (movingOneElement) {
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    selectedElementIndex = diagramView.getElementPainters().indexOf(elementPainter);
                    System.out.println("Pomeram element: " + elementPainter.getElement().getName());
                }
            }
        }

        startPoint = diagramView.getAbsolutePoint(x, y);
        lastPointX = (int) diagramView.getAbsolutePoint(x, y).getX();
        lastPointY = (int) diagramView.getAbsolutePoint(x, y).getY();
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (movingOneElement) {
            ((Interclass) diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(diagramView.getAbsolutePoint(x, y));
        } else if (movingElements) {
            int deltaX = (int) (diagramView.getAbsolutePoint(x, y).getX() - lastPointX);
            int deltaY = (int) (diagramView.getAbsolutePoint(x, y).getY() - lastPointY);
            System.out.println(deltaX + ", " + deltaY);

            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter.getElement() instanceof Interclass) {
                    if (elementPainter.getElement().isSelected()) {

                        int newX = (int) (((Interclass) elementPainter.getElement()).getPosition().getX() + deltaX / diagramView.getScale());
                        int newY = (int) (((Interclass) elementPainter.getElement()).getPosition().getY() + deltaY / diagramView.getScale());
                        ((Interclass) elementPainter.getElement()).setPosition(new Point(newX, newY));
                    }
                }
            }
            lastPointX = (int) diagramView.getAbsolutePoint(x, y).getX();
            lastPointY = (int) diagramView.getAbsolutePoint(x, y).getY();
        } else {
            Point endPoint = diagramView.getAbsolutePoint(x, y);
            double offsetX = -(startPoint.x - endPoint.getX());
            double offsetY = -(startPoint.y - endPoint.getY());

            double xNew = (diagramView.getDeltaX() + offsetX * diagramView.getScale());
            double yNew = (diagramView.getDeltaY() + offsetY * diagramView.getScale());

            diagramView.setDeltaX((int) xNew);
            diagramView.setDeltaY((int) yNew);
        }

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u MoveState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
        if (movingOneElement) {
            boolean validan = true;
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (!diagramView.getElementPainters().get(selectedElementIndex).getElement().equals(elementPainter.getElement())) {
                    if (elementPainter.getElement() instanceof Interclass && elementPainter.getShape().intersects((Rectangle2D) diagramView.getElementPainters().get(selectedElementIndex).getShape())) {
                        validan = false;
                    }
                }
            }
            if (!validan) {
                System.out.println("Nije validan");
                ((Interclass) diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(startPoint);
            } else {
                System.out.println("Validan");
                ((Interclass) diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(diagramView.getAbsolutePoint(x, y));
            }

        }
        selectedElementIndex = -1;
        movingElements = false;
        movingOneElement = false;
    }
}
