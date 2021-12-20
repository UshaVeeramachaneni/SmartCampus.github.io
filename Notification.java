package app.college.smartcampus.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Notification implements Serializable {

    private String type;
    private String title;
    private String content;
    @SerializedName("created_on")
    private String createdOn;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
