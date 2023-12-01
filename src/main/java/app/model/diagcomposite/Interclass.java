package app.model.diagcomposite;

import app.model.diagcomposite.DiagramElement;

import javax.swing.text.Position;

public abstract class Interclass extends DiagramElement {

    private String name;
    private Visibility visibility;
    private Position position;
    private int size;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
