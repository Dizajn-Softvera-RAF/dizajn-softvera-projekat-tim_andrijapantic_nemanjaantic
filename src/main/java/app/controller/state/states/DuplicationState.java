package app.controller.state.states;

import app.controller.state.State;
import app.model.classcontent.Attribute;
import app.model.classcontent.ClassContent;
import app.model.classcontent.EnumType;
import app.model.classcontent.Method;
import app.model.diagimplementation.interclass.*;
import app.view.mainframe.DiagramView;
import app.view.mainframe.MainFrame;
import app.view.painters.ClassPainter;
import app.view.painters.ElementPainter;
import app.view.painters.EnumPainter;
import app.view.painters.InterfacePainter;

import java.awt.*;
import java.util.ArrayList;


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
                    ArrayList<ClassContent> content = new ArrayList<>();
                    for (ClassContent classContent: copyFrom.getContent()) {
                        if (classContent instanceof Method)
                            content.add(new Method(classContent.getName(), classContent.getType(), classContent.getVisibility()));
                        else if (classContent instanceof Attribute)
                            content.add(new Attribute(classContent.getName(), classContent.getType(), classContent.getVisibility()));
                    }
                    newKlasa.setContent(content);
                    newKlasa.setInitialColor(copyFrom.getInitialColor());
                    newKlasa.setCurrentColor(copyFrom.getCurrentColor());
                    newKlasa.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), newKlasa));
                    newKlasa.addSubscriber(diagramView);
                    painterToCreate = new ClassPainter(newKlasa);
                    painterToCreate.setElement(newKlasa);
                } else if (elementPainter.getElement() instanceof Interface) {
                    Interface copyFrom = (Interface) elementPainter.getElement();
                    Point newPoint = new Point((int) (((Interface) elementPainter.getElement()).getPosition().getX()+15),
                            (int) (((Interface) elementPainter.getElement()).getPosition().getY()-15));
                    Interface newInterface = new Interface(newPoint, copyFrom.getParent(), copyFrom.getName(), copyFrom.getStroke());
                    ArrayList<ClassContent> content = new ArrayList<>();
                    for (ClassContent classContent: copyFrom.getContent()) {
                        if (classContent instanceof Method)
                            content.add(new Method(classContent.getName(), classContent.getType(), classContent.getVisibility()));
                    }
                    newInterface.setContent(content);
                    newInterface.setInitialColor(copyFrom.getInitialColor());
                    newInterface.setCurrentColor(copyFrom.getCurrentColor());
                    newInterface.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), newInterface));
                    newInterface.addSubscriber(diagramView);
                    painterToCreate = new InterfacePainter(newInterface);
                    painterToCreate.setElement(newInterface);
                } else if (elementPainter.getElement() instanceof EnumComp) {
                    EnumComp copyFrom = (EnumComp) elementPainter.getElement();
                    Point newPoint = new Point((int) (((EnumComp) elementPainter.getElement()).getPosition().getX()+15),
                            (int) (((EnumComp) elementPainter.getElement()).getPosition().getY()-15));
                    EnumComp newEnum = new EnumComp(newPoint, copyFrom.getParent(), copyFrom.getName(), copyFrom.getStroke());
                    ArrayList<ClassContent> content = new ArrayList<>();
                    for (ClassContent classContent: copyFrom.getContent()) {
                       if (classContent instanceof EnumType)
                            content.add(new EnumType(classContent.getName()));
                    }
                    newEnum.setContent(content);
                    newEnum.setInitialColor(copyFrom.getInitialColor());
                    newEnum.setCurrentColor(copyFrom.getCurrentColor());
                    newEnum.setMyNodeMutable(MainFrame.getInstance().getClassyTree().addDiagramElementChild(diagramView.getMyNodeMutable(), newEnum));
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
