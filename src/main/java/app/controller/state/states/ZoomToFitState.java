package app.controller.state.states;

import app.controller.state.State;
import app.model.diagcomposite.Interclass;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;

public class ZoomToFitState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {

        double gornjeX = 10000000;
        double gornjeY = 10000000;
        double donjeX = -10000000;
        double donjeY = -10000000;
        int counter = 0;

        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter.getElement() instanceof Interclass) {
                counter++;
                gornjeX = Math.min(gornjeX, elementPainter.getShape().getBounds().getMinX());
                gornjeY = Math.min(gornjeY, elementPainter.getShape().getBounds().getMinY());
                donjeX = Math.max(donjeX, elementPainter.getShape().getBounds().getMaxX());
                donjeY = Math.max(donjeY, elementPainter.getShape().getBounds().getMaxY());
            }

        }

        double width = donjeX - gornjeX;
        double height = donjeY - gornjeY;

        double noviScale = Math.min((diagramView.getWidth()/1.15)/width, (diagramView.getHeight()/1.15)/height);
        if (noviScale < 0.3)
            noviScale = 0.3;
        else if (noviScale > 2.0)
            noviScale = 2.0;

        int deltaX;
        int deltaY;
        if (counter==1) {
            deltaX = (int) (diagramView.getWidth()-(donjeX+width/3)*noviScale);
            deltaY = (int) (diagramView.getHeight()-(donjeY+height/3)*noviScale);
        }
        else {
            deltaX = (int) (diagramView.getWidth() - (donjeX + width)/2*noviScale);
            deltaY = (int) (diagramView.getHeight() - (donjeY + height)/2*noviScale);
        }



        diagramView.setScale(noviScale);
        diagramView.setDeltaX(deltaX);
        diagramView.setDeltaY(deltaY);
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
