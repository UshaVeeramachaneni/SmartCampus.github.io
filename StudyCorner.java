package app.college.smartcampus.models;

import java.io.Serializable;

public class StudyCorner implements Serializable {

    private String name;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
