package app.model.classcontent;

import app.model.diagcomposite.Visibility;

public class Method extends ClassContent{


    public Method(String name, String type, Visibility visibility) {
        super(name, type, visibility);
    }


    public String getMethodString() {
        String visibility;
        if (getVisibility().equals(Visibility.PUBLIC))
            visibility = "+";
        else if (getVisibility().equals(Visibility.PRIVATE))
            visibility = "-";
        else
            visibility = "#";
        return visibility + " " + getName() + "() : " + getType();
    }
}
