package app.model.state.states;

import app.model.state.State;
import app.view.mainframe.DiagramView;

import java.awt.*;

public class ZoomInState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        diagramView.setDeltaX(x/5);
        diagramView.setDeltaY(y/5);
        diagramView.setZoomIn();
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u ZoomInState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u ZoomInState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
