package app.model.classcontent;

import app.model.diagcomposite.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Attribute extends ClassContent{


    public Attribute(String name, String type, Visibility visibility) {
        super(name, type, visibility);

    }

    public Attribute() {
    }
    @JsonIgnore
    public String attributeString() {
        String visibility;
        if (getVisibility().equals(Visibility.PUBLIC))
            visibility = "+";
        else if (getVisibility().equals(Visibility.PRIVATE))
            visibility = "-";
        else
            visibility = "#";
        return visibility + " " + getName() + " : " + getType();
    }
}
