package app.view.painters;

import app.model.diagcomposite.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {

    DiagramElement element;
    String name;

    public ElementPainter() {
    }

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(DiagramElement element, Point point);

    public DiagramElement getElement() {
        return element;
    }

    public void setElement(DiagramElement element) {
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
