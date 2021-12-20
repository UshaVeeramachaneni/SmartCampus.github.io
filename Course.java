package app.college.smartcampus.models;

import java.io.Serializable;

public class Course implements Serializable {

    private String branch;
    private Integer seats;

    public Course(String branch, Integer seats) {
        this.branch = branch;
        this.seats = seats;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
