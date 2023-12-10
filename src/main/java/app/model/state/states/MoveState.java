package app.model.state.states;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.interclass.Klasa;
import app.model.message.Message;
import app.model.message.MessageGenerator;
import app.model.message.PossibleErr;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ClassPainter;
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
                    selectedElementIndex = diagramView.getElementPainters().indexOf(elementPainter);
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
            ((Interclass)diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(diagramView.getAbsolutePoint(x, y));
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
            boolean vracaj = false;
            if (movingOneElement) {

                ((Interclass)diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(diagramView.getAbsolutePoint(x, y));
                for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                    if (!diagramView.getElementPainters().get(selectedElementIndex).getElement().equals(elementPainter.getElement())) {
                        if (elementPainter.getElement() instanceof Interclass && elementPainter.getShape().intersects((Rectangle2D) diagramView.getElementPainters().get(selectedElementIndex).getShape())) {
                            //((Interclass)diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(startPoint);
                            vracaj = true;
                           // break;
                        }
                    }
                }
            }
            if (vracaj) {
                for (ElementPainter e: diagramView.getElementPainters()) {
                    if (e.getElement() instanceof Connection && ((((Connection) e.getElement()).getFromInterclass()).equals(diagramView.getElementPainters().get(selectedElementIndex)))) {
                        ((Connection) e.getElement()).setStartPoint(startPoint);
                    }
                }
                ((Interclass) diagramView.getElementPainters().get(selectedElementIndex).getElement()).setPosition(startPoint);

            }
            selectedElementIndex = -1;
            movingElements = false;
            movingOneElement = false;
        }
}
