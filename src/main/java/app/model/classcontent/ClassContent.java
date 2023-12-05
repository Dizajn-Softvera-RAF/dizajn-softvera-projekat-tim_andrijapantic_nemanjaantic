package app.model.classcontent;

import app.model.diagcomposite.Visibility;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassContent {

    private String name;



    public ClassContent(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
