package app.model.classcontent;

import app.model.diagcomposite.Visibility;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class EnumType extends ClassContent{


    public EnumType(String name) {
        super(name.toUpperCase());
    }

    public EnumType() {}


    public String getEnumerableString() {
        return getName();
    }
}
