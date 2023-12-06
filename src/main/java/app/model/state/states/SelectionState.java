package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;
import app.view.painters.SelectionPainter;

import java.awt.*;

public class SelectionState implements State {

    private static SelectionPainter lasso;
    private Point startPoint, endPoint;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {


        if (lasso == null) {
            System.out.println("pravi lasso");
             startPoint = diagramView.getAbsolutePoint(x, y);

            lasso = new SelectionPainter(startPoint, startPoint);
            lasso.setStartPoint((int)startPoint.getX(), (int) startPoint.getY());
            diagramView.getElementPainters().add(lasso);
        }
        System.out.println("Trenutno si u SelectState i kliknuo si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

        Point e = diagramView.getAbsolutePoint(x, y);


        if (lasso != null) {
            endPoint = e;
            lasso.setEndPoint((int)e.getX(), (int)e.getY());


            diagramView.repaint();
        }
        //System.out.println("Trenutno si u SelectState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

        Point e = diagramView.getAbsolutePoint(x, y);

        if (lasso != null) {
            if(lasso.getToPaint() == null){
                for(ElementPainter elementPainter: diagramView.getElementPainters()){
                    if(!(elementPainter instanceof SelectionPainter) && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))){
                        System.out.println("element u ovoj tacki je:" + elementPainter.getElement().getName());
                    }


                }

            } else {
                Point endPoint = e;
                lasso.setEndPoint((int) e.getX(), (int) e.getY());

                for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                    if (elementPainter.getElement() instanceof Interclass) {

                        if (lasso.getToPaint().contains(((Interclass) elementPainter.getElement()).getPosition())) {
                            System.out.println("Element u lasso-u:" + elementPainter.getElement().getName());
                        }

                    }

                }

                diagramView.getElementPainters().remove(lasso);
                lasso = null;
                diagramView.repaint();
            }
        }
        System.out.println("Trenutno si u SelectState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
