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
    private Interclass selectedElement;
    private ElementPainter selectedPainter;
    private Point startPoint;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutne koordinate: (" + x + "," + y + ")");
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                movingElements = true;
                selectedElement = (Interclass) elementPainter.getElement();
                selectedPainter = elementPainter;
                System.out.println("Pomeram element: " + elementPainter.getElement().getName());
            }
        }
        startPoint = diagramView.getAbsolutePoint(x, y);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (movingElements) {
            selectedElement.setPosition(diagramView.getAbsolutePoint(x, y));
        } else {
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
        if (movingElements) {
            selectedElement.setPosition(diagramView.getAbsolutePoint(x, y));
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (!selectedElement.getName().equals(elementPainter.getElement().getName())) {
                    if (elementPainter.getElement() instanceof Interclass && elementPainter.getShape().intersects((Rectangle2D) selectedPainter.getShape())) {
                        selectedElement.setPosition(startPoint);
                    }
                }
            }
            selectedElement = null;
            movingElements = false;
        }
    }
}
