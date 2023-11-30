package app.controller;

import app.view.mainframe.DiagramView;
import app.view.mainframe.PackageView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener,MouseMotionListener {
    private DiagramView diagramView;

    public MouseController(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        diagramView.getParentView().misOtpusten(e.getX(), e.getY(), diagramView);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        diagramView.getParentView().misPovucen(e.getX(),e.getY(), diagramView);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        diagramView.getParentView().misKliknut(e.getX(), e.getY(), diagramView);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public DiagramView getDiagramView() {
        return diagramView;
    }

    public void setDiagramView(DiagramView diagramView) {
        this.diagramView = diagramView;
    }


}
