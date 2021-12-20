package app.college.smartcampus.models;

import java.io.Serializable;

public class Management implements Serializable {

    private String title;
    private String description;


    public Management(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
