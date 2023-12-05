package app.model.classcontent;

import app.model.diagcomposite.Visibility;

public abstract class ClassContent {

    private String name;

    private Visibility visibility;
    private String type;

    public ClassContent(String name, String type, Visibility visibility) {
        this.name = name;
        this.visibility = visibility;
        this.type = type;
    }

    public ClassContent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
