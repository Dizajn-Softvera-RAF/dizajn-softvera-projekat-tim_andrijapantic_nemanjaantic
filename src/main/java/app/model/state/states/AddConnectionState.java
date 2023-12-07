package app.model.state.states;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AddConnectionState implements State {

    private boolean startExists = false;

    Connection link;
    private ArrayList<Point> startPoints;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    startExists = true;
                    break;
                }
            }
        }
        if (startExists) {
            if (MainFrame.getInstance().getConnectionType()==0) {
                link = new Aggregation("Aggregation " + new Random().nextInt(100), diagramView.getDiagramNode());
            }

            System.out.println("Trenutno si u ConnectionState i kliknuo si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                    if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                        System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                        MainFrame.getInstance().setChildToCreateType("aggregation");
                        diagramView.getElementPainters().add(0, new AggregationPainter((Aggregation) link));
                        diagramView.setCurrentLink(link);
                        link.setStartPoint(diagramView.getAbsolutePoint(x, y));
                        link.setEndPoint(diagramView.getAbsolutePoint(x, y));
                        MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), link);
                        link.setFromInterclass((Interclass) elementPainter.getElement());
                        // link.setToInterclass(new Klasa(null, diagramView.getAbsolutePoint(x, y)));
                        startPoints = ((Interclass) elementPainter.getElement()).getConnectionsDots();
                        link.addSubscriber(diagramView);
                        diagramView.getDiagramNode().addChild(link);
                        break;
                    }
                }
            }
        }



    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (startExists) {
            diagramView.getCurrentLink().setToInterclass(new Klasa(null, diagramView.getAbsolutePoint(x, y)));
            diagramView.getCurrentLink().setEndPoint(diagramView.getAbsolutePoint(x,y));
        }


    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        if (startExists) {
            boolean isOnTheme = false;
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                    if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                       //// System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                        diagramView.getCurrentLink().setToInterclass((Interclass) elementPainter.getElement());
                        Point[] tacke = diagramView.getTwoClosestPoints(startPoints, ((Interclass) elementPainter.getElement()).getConnectionsDots());
                        diagramView.getCurrentLink().setStartPoint(tacke[0]);
                        diagramView.getCurrentLink().setEndPoint(tacke[1]);

                        isOnTheme = true;
                        break;
                    }
                }

            }
            if (isOnTheme) {
                System.out.println("Povezani su " + diagramView.getCurrentLink().getFromInterclass().getName() + " i " + diagramView.getCurrentLink().getToInterclass().getName());
                diagramView.setCurrentLink(null);
            } else {
                diagramView.getElementPainters().remove(0);
                diagramView.getDiagramNode().removeChild(diagramView.getCurrentLink());
            }
        }
        startExists = false;


    }


}
