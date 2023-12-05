package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;

import java.awt.*;

public class MoveState implements State {
    private boolean movingElements = false;
    private Interclass selectedElement;
    private Point startPoint;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutne koordinate: (" + x + "," + y + ")");
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                movingElements = true;
                selectedElement = (Interclass) elementPainter.getElement();
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
        }
    }
}
