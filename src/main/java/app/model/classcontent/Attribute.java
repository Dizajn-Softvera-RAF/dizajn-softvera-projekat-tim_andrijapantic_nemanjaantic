package app.model.classcontent;

import app.model.diagcomposite.Visibility;

public class Attribute extends ClassContent{


    public Attribute(String name, String type, Visibility visibility) {
        super(name, type, visibility);

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
