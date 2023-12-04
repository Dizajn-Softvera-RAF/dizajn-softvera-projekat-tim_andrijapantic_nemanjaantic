package app.model.classcontent;

import app.model.diagcomposite.Visibility;

public class Attribute extends ClassContent{

    private Visibility visibility;
    private String type;

    public Attribute(String name, String type, Visibility visibility) {
        super(name);
        this.type = type;
        this.visibility = visibility;
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

    public String getAttributeString() {
        String visibility;
        if (getVisibility().equals(Visibility.PUBLIC))
            visibility = "+";
        else
            visibility = "-";
        return visibility + " " + getName() + " : " + getType();
    }
}
