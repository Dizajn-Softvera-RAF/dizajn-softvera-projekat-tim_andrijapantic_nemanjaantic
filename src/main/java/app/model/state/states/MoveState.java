package app.model.state.states;

import app.model.state.State;
import app.view.mainframe.DiagramView;

import java.awt.*;

public class MoveState implements State {

    private Point startPoint;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutne koordinate: (" + x + "," + y + ")");
        startPoint = diagramView.getAbsolutePoint(x, y);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        Point endPoint = diagramView.getAbsolutePoint(x, y);
        double offsetX = - (startPoint.x - endPoint.getX());
        double offsetY = - (startPoint.y - endPoint.getY());

        double xNew = (diagramView.getDeltaX() + offsetX*diagramView.getScale());
        double yNew = (diagramView.getDeltaY() + offsetY*diagramView.getScale());

        diagramView.setDeltaX((int) xNew);
        diagramView.setDeltaY((int) yNew);

        diagramView.repaint();
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u MoveState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
