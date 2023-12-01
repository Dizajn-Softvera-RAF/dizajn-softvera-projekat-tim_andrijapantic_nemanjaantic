package app.view.painters;

import app.model.diagcomposite.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {

    public ElementPainter() {
    }

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(DiagramElement element, Point point);


}
