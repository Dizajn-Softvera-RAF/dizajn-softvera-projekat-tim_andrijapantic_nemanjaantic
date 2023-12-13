package app.controller.state.states;

import app.controller.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.ElementPainter;
import app.view.painters.SelectionPainter;

import java.awt.*;

public class SelectionState implements State {

    private static SelectionPainter lasso;
    private Point startPoint;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {

        if (lasso == null) {
            startPoint = diagramView.getAbsolutePoint(x, y);

            lasso = new SelectionPainter(startPoint, startPoint);
            lasso.setStartPoint((int) startPoint.getX(), (int) startPoint.getY());
            diagramView.getElementPainters().add(lasso);
        }
        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if ((!(elementPainter instanceof SelectionPainter)) && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                System.out.println("Element na ovoj tacki je: " + elementPainter.getElement().getName());
            }
        }
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

        Point e = diagramView.getAbsolutePoint(x, y);

        boolean anyChanged = false;
        if (lasso != null) {
            lasso.setEndPoint((int) e.getX(), (int) e.getY());

            if (lasso.getToPaint() != null) {
                for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                    if (!(elementPainter instanceof SelectionPainter)) {
                        if (!lasso.getToPaint().intersects(elementPainter.getShape().getBounds()) && elementPainter.getElement().isSelected()) {
                            elementPainter.getElement().setSelected(false);
                            anyChanged = true;
                        }
                        if (lasso.getToPaint().intersects(elementPainter.getShape().getBounds())) {
                            elementPainter.getElement().setSelected(true);
                            anyChanged = true;

                        }

                    }

                }
            }

        }
        if (!anyChanged)
            diagramView.repaint();
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

        Point e = diagramView.getAbsolutePoint(x, y);

        if (lasso != null) {
            if (lasso.getToPaint() == null) {
                diagramView.getElementPainters().remove(lasso);
                lasso = null;
                //Deselect svih selektovanih se vrsi ponovnim klikom na jednom od selektovanih
                for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                    if ((!(elementPainter instanceof SelectionPainter)) && !(elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y)) &&
                            elementPainter.getElement().isSelected())) {
                        elementPainter.getElement().setSelected(false);
                    }
                    if ((!(elementPainter instanceof SelectionPainter)) && elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                        if (!elementPainter.getElement().isSelected())
                            elementPainter.getElement().setSelected(true);
                        else
                            elementPainter.getElement().setSelected(false);

                    }


                }

            } else {
                lasso.setEndPoint((int) e.getX(), (int) e.getY());
                boolean anyChanged = false;
                for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                    if (!(elementPainter instanceof SelectionPainter)) {
                        if (!lasso.getToPaint().intersects(elementPainter.getShape().getBounds()) && elementPainter.getElement().isSelected()) {
                            elementPainter.getElement().setSelected(false);
                            anyChanged = true;
                        }
                        if (lasso.getToPaint().intersects(elementPainter.getShape().getBounds())) {
                            elementPainter.getElement().setSelected(true);
                            anyChanged = true;

                        }

                    }

                }

                diagramView.getElementPainters().remove(lasso);
                lasso = null;
                if (!anyChanged)
                    diagramView.repaint();
            }
        }
        System.out.println("Trenutno si u SelectState i pustio si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
    }
}
