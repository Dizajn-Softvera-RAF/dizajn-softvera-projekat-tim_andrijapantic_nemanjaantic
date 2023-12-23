package app.model.classcontent;

import app.model.diagcomposite.Connection;
import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Attribute.class, name = "Attribute"),
        @JsonSubTypes.Type(value = Method.class, name = "Method"),
        @JsonSubTypes.Type(value = EnumType.class, name = "EnumType"),
})
public abstract class ClassContent {

    private String name;

    private Visibility visibility;
    private String type;

    public ClassContent(String name, String type, Visibility visibility) {
        this.name = name;
        this.visibility = visibility;
        this.type = type;
    }

    public ClassContent() {}

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
