package app.college.smartcampus.models;

import java.io.Serializable;

public class Attendance implements Serializable {

    private String day;
    private String date;
    private String attend;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }
}
