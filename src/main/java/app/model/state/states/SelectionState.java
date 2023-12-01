package app.model.state.states;

import app.model.state.State;
import app.view.mainframe.DiagramView;

public class SelectionState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u SelectState i kliknuo si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u SelectState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u SelectState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
