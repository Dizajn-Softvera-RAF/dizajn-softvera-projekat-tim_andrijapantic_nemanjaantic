package app.controller.state;

import app.view.mainframe.DiagramView;

public interface State {
    void misKliknut(int x, int y, DiagramView diagramView);
    void misPovucen(int x, int y, DiagramView diagramView);
    void misOtpusten(int x, int y, DiagramView diagramView);
}
