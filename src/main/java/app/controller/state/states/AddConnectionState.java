package app.controller.state.states;

import app.controller.state.State;
import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.*;

import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AddConnectionState implements State {

    private boolean startExists = false;
    private ArrayList<Point> listaTacaka = new ArrayList<>();

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
                link = new Aggregation("Aggregation" + new Random().nextInt(100), diagramView.getDiagramNode());
            } else if (MainFrame.getInstance().getConnectionType()==1) {
                link = new Composition("Composition" + new Random().nextInt(100), diagramView.getDiagramNode());
            } else if (MainFrame.getInstance().getConnectionType()==3) {
                link = new Generalization("Generalization" + new Random().nextInt(100), diagramView.getDiagramNode());
            } else if (MainFrame.getInstance().getConnectionType()==2) {
                link = new Dependency("Dependency" + new Random().nextInt(100), diagramView.getDiagramNode());
            }
            link.setInitialColor(0);
            link.setCurrentColor(0);

            System.out.println("Trenutno si u ConnectionState i kliknuo si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
            for (ElementPainter elementPainter: diagramView.getElementPainters()) {
                if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                    if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                        System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                        if (link instanceof Aggregation) {
                            MainFrame.getInstance().setChildToCreateType("aggregation");
                            diagramView.getElementPainters().add(0, new AggregationPainter((Aggregation) link));
                        } else if (link instanceof Composition) {
                            MainFrame.getInstance().setChildToCreateType("composition");
                            diagramView.getElementPainters().add(0, new CompositionPainter((Composition) link));
                        } else if (link instanceof Generalization) {
                            MainFrame.getInstance().setChildToCreateType("generalization");
                            diagramView.getElementPainters().add(0, new GeneralizationPainter((Generalization) link));
                        } else if (link instanceof Dependency) {
                            MainFrame.getInstance().setChildToCreateType("dependency");
                            diagramView.getElementPainters().add(0, new DependencyPainter((Dependency) link));
                        }

                        diagramView.setCurrentLink(link);

                        ArrayList<Point> tacka = new ArrayList<>();
                        tacka.add(diagramView.getAbsolutePoint(x, y));
                        listaTacaka = ((Interclass)elementPainter.getElement()).getConnectionsDots();
                        Point[] tacke = diagramView.getTwoClosestPoints(listaTacaka, tacka);

                        link.setStartPoint(tacke[0]);
                        link.setEndPoint(diagramView.getAbsolutePoint(x, y));
                        link.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addExistingChild(diagramView.getMyNodeMutable(), link));
                        link.setFromInterclass((Interclass) elementPainter.getElement());
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
            ArrayList<Point> tacka2 = new ArrayList<>();
            tacka2.add(diagramView.getAbsolutePoint(x, y));

            Point[] tacke = diagramView.getTwoClosestPoints(listaTacaka, tacka2);
            diagramView.getCurrentLink().setStartPoint(tacke[0]);
            diagramView.getCurrentLink().setEndPoint(diagramView.getAbsolutePoint(x,y));
        }


    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        if (startExists) {
            boolean isOnInterclass = false;
            for (ElementPainter elementPainter : diagramView.getElementPainters()) {
                if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                    if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {

                        if (diagramView.getCurrentLink().getFromInterclass().equals(elementPainter.getElement()))
                            isOnInterclass = false;
                        else {
                            isOnInterclass = true;

                            diagramView.getCurrentLink().setToInterclass((Interclass) elementPainter.getElement());
                            Point[] tacke = diagramView.getTwoClosestPoints(startPoints, ((Interclass) elementPainter.getElement()).getConnectionsDots());
                            diagramView.getCurrentLink().setStartPoint(tacke[0]);
                            diagramView.getCurrentLink().setEndPoint(tacke[1]);
                            if (diagramView.getCurrentLink() instanceof Generalization) {
                                if (diagramView.getCurrentLink().getFromInterclass() instanceof Klasa &&
                                        diagramView.getCurrentLink().getToInterclass() instanceof Klasa)
                                    diagramView.getCurrentLink().setExtentionType("extend");
                                else if (diagramView.getCurrentLink().getFromInterclass() instanceof Klasa &&
                                        diagramView.getCurrentLink().getToInterclass() instanceof Interface)
                                    diagramView.getCurrentLink().setExtentionType("implement");
                                else if (diagramView.getCurrentLink().getFromInterclass() instanceof Interface &&
                                        diagramView.getCurrentLink().getToInterclass() instanceof Interface)
                                    diagramView.getCurrentLink().setExtentionType("extend");
                            }

                        }
                        break;
                    }
                }

            }
            if (isOnInterclass) {
                System.out.println("Povezani su " + diagramView.getCurrentLink().getFromInterclass().getName() + " i " + diagramView.getCurrentLink().getToInterclass().getName());
                diagramView.setCurrentLink(null);
            } else {
                diagramView.getElementPainters().remove(0);
                diagramView.getDiagramNode().removeChild(diagramView.getCurrentLink());
                DefaultTreeModel model = MainFrame.getInstance().getClassyTree().getTreeModel();
                model.removeNodeFromParent(diagramView.getCurrentLink().getMyNodeMutable());
            }
        }
        startExists = false;


    }


}
