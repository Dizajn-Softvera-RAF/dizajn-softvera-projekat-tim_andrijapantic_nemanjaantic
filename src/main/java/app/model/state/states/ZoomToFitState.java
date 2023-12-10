package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;

public class ZoomToFitState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {

        double gornjeX = 10000000;
        double gornjeY = 10000000;
        double donjeX = -10000000;
        double donjeY = -10000000;

        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass) {
                gornjeX = Math.min(gornjeX, elementPainter.getShape().getBounds().getMinX());
                gornjeY = Math.min(gornjeY, elementPainter.getShape().getBounds().getMinY());
                donjeX = Math.max(donjeX, elementPainter.getShape().getBounds().getMaxX());
                donjeY = Math.max(donjeY, elementPainter.getShape().getBounds().getMaxY());
            }

        }

        double width = donjeX - gornjeX;
        double height = donjeY - gornjeY;

        double noviScale = Math.min((diagramView.getWidth()/1.15)/width, (diagramView.getHeight()/1.15)/height);
        int deltaX = (int) (diagramView.getWidth() - noviScale * width/2) / 4;
        int deltaY = (int) (diagramView.getHeight() - noviScale * height/2) / 4;

        diagramView.setScale(noviScale);
        diagramView.setDeltaX(deltaX);
        diagramView.setDeltaY(deltaY);

        diagramView.repaint();
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u ZoomToFitState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        System.out.println("Trenutno si u ZoomToFitState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
