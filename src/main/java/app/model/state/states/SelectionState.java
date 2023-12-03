package app.model.state.states;

import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;
import app.view.painters.SelectionPainter;

import java.awt.*;

public class SelectionState implements State {

    private static SelectionPainter lasso;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        if (lasso == null) {
            System.out.println("pravi lasso");
            Point newPoint = diagramView.getAbsolutePoint(x, y);
            lasso = new SelectionPainter(newPoint, newPoint);
            lasso.setStartPoint(newPoint);
            diagramView.getElementPainters().add(lasso);
        }
        System.out.println("Trenutno si u SelectState i kliknuo si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

        Point e = diagramView.getAbsolutePoint(x, y);

        if (lasso != null) {
            Point endPoint = e;
            lasso.setEndPoint(endPoint);
            int x2 = Math.abs((int) lasso.getStartPoint().getX() - (int) lasso.getEndPoint().getX());
            int y2 = Math.abs((int) lasso.getStartPoint().getY() - (int) lasso.getEndPoint().getY());
            lasso.setHeight(x2);
            lasso.setWidth(y2);
            diagramView.repaint();
        }
        System.out.println("Trenutno si u SelectState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

        Point e = diagramView.getAbsolutePoint(x, y);

        if (lasso != null) {
            Point endPoint = e;
            lasso.setEndPoint(endPoint);


            diagramView.getElementPainters().remove(lasso);
            lasso = null;
            diagramView.repaint();
        }
        System.out.println("Trenutno si u SelectState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
