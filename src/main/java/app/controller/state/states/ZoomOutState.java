package app.controller.state.states;

import app.controller.state.State;
import app.view.mainframe.DiagramView;

import java.awt.*;

public class ZoomOutState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        diagramView.setDeltaX(x/4);
        diagramView.setDeltaY(y/4);
        diagramView.setZoomOut();
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u ZoomOutState i prevuko si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u ZoomOutState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
