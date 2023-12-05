package app.model.classcontent;

import app.model.diagcomposite.Visibility;

public class EnumType extends ClassContent{


    public EnumType(String name) {
        super(name.toUpperCase());
    }


    public String getEnumerableString() {
        return getName();
    }
}
