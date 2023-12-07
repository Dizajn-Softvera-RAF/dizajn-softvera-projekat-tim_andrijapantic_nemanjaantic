package app.model.state.states;

import app.model.diagcomposite.Interclass;
import app.model.diagimplementation.interclass.*;
import app.model.state.State;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.ClassPainter;
import app.view.painters.ElementPainter;
import app.view.painters.EnumPainter;
import app.view.painters.InterfacePainter;

import java.awt.*;


public class DuplicationState implements State {

    private boolean elementExists = false;
    private ElementPainter painterToCreate;
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for(ElementPainter elementPainter: diagramView.getElementPainters()){
            if(elementPainter.elementAt(elementPainter.getElement(), diagramView.getAbsolutePoint(x, y))){
                System.out.println("element u ovoj tacki je:" + elementPainter.getElement().getName());
                elementExists = true;
                if (elementPainter.getElement() instanceof Klasa) {
                    Klasa copyFrom = (Klasa) elementPainter.getElement();
                    Point newPoint = new Point((int) (((Klasa) elementPainter.getElement()).getPosition().getX()+15),
                            (int) (((Klasa) elementPainter.getElement()).getPosition().getY()-15));
                    Klasa newKlasa = new Klasa(newPoint, copyFrom.getParent(), copyFrom.getName(), copyFrom.getStroke());
                    newKlasa.setContent(copyFrom.getContent());
                    newKlasa.setInitialColor(copyFrom.getInitialColor());
                    newKlasa.setCurrentColor(copyFrom.getCurrentColor());
                    MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), newKlasa);
                    newKlasa.addSubscriber(diagramView);
                    painterToCreate = new ClassPainter(newKlasa);
                    painterToCreate.setElement(newKlasa);
                } else if (elementPainter.getElement() instanceof Interface) {
                    Interface copyFrom = (Interface) elementPainter.getElement();
                    Point newPoint = new Point((int) (((Interface) elementPainter.getElement()).getPosition().getX()+15),
                            (int) (((Interface) elementPainter.getElement()).getPosition().getY()-15));
                    Interface newInterface = new Interface(newPoint, copyFrom.getParent(), copyFrom.getName(), copyFrom.getStroke());
                    newInterface.setContent(copyFrom.getContent());
                    newInterface.setInitialColor(copyFrom.getInitialColor());
                    newInterface.setCurrentColor(copyFrom.getCurrentColor());
                    MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), newInterface);
                    newInterface.addSubscriber(diagramView);
                    painterToCreate = new InterfacePainter(newInterface);
                    painterToCreate.setElement(newInterface);
                } else if (elementPainter.getElement() instanceof EnumComp) {
                    EnumComp copyFrom = (EnumComp) elementPainter.getElement();
                    Point newPoint = new Point((int) (((EnumComp) elementPainter.getElement()).getPosition().getX()+15),
                            (int) (((EnumComp) elementPainter.getElement()).getPosition().getY()-15));
                    EnumComp newEnum = new EnumComp(newPoint, copyFrom.getParent(), copyFrom.getName(), copyFrom.getStroke());
                    newEnum.setContent(copyFrom.getContent());
                    newEnum.setInitialColor(copyFrom.getInitialColor());
                    newEnum.setCurrentColor(copyFrom.getCurrentColor());
                    MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), newEnum);
                    newEnum.addSubscriber(diagramView);
                    painterToCreate = new EnumPainter(newEnum);
                    painterToCreate.setElement(newEnum);
                }
                break;
            }

        }
        if (elementExists)
            diagramView.getElementPainters().add(painterToCreate);
        elementExists = false;
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
