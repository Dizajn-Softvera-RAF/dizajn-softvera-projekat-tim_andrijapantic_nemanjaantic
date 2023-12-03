package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.interclass.Klasa;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.painters.*;

public class AddConnectionState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        Aggregation link = new Aggregation();
        //System.out.println("Trenutno si u ConnectionState i kliknuo si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());
        for (ElementPainter elementPainter: diagramView.getElementPainters()) {
            if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    link.setFromInterclass((Interclass) elementPainter.getElement());
                    link.setToInterclass(new Klasa(null, diagramView.getAbsolutePoint(x, y)));
                    break;
                }
            }
        }
        diagramView.getElementPainters().add(0, new AggregationPainter(link));
        diagramView.setCurrentLink(link);
        diagramView.getDiagramNode().addChild(link);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        diagramView.getCurrentLink().setToInterclass(new Klasa(null, diagramView.getAbsolutePoint(x, y)));
        diagramView.repaint();
       // System.out.println("Trenutno si u ConnectionState i povukao si na tacku: (" + x + "," + y + ") na dijagramu: " + diagramView.getDiagramNode().getName());

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        boolean isOnTheme = false;
        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                if (elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))) {
                    System.out.println("Element na toj poziciji je: " + elementPainter.getElement().getName());
                    diagramView.getCurrentLink().setToInterclass((Interclass) elementPainter.getElement());
                    isOnTheme = true;
                    break;
                }
            }

        }
        if (isOnTheme) {
            System.out.println("Povezani su " + diagramView.getCurrentLink().getFromInterclass().getName() + " i " + diagramView.getCurrentLink().getToInterclass().getName());
            diagramView.setCurrentLink(null);
            diagramView.repaint(); //trebalo bi nekako preko observera da se posalje poruka diagramnodu i on da updata diagramview umesto ovako rucno
        } else {
            diagramView.getElementPainters().remove(0);
            diagramView.getDiagramNode().removeChild(diagramView.getCurrentLink());
        }


    }
}
