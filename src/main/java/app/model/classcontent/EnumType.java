package app.model.classcontent;

import app.model.diagcomposite.Visibility;

public class EnumType extends ClassContent{

    private Visibility visibility = Visibility.PUBLIC;
    public EnumType(String name) {
        super(name.toUpperCase());
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getEnumerableString() {
        return getName();
    }
}
