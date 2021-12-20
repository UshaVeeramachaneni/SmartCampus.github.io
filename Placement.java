package app.college.smartcampus.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Placement implements Serializable {

    private String year;
    private List<PlacementData> data;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<PlacementData> getData() {
        return data;
    }

    public void setData(List<PlacementData> data) {
        this.data = data;
    }

    public static class PlacementData implements Serializable {
        private String id;
        private String company;
        private String year;
        @SerializedName("students_count")
        private String studentsCount;
        private String ce;
        private String eee;
        private String mech;
        private String ece;
        private String cse;
        private String date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getStudentsCount() {
            return studentsCount;
        }

        public void setStudentsCount(String studentsCount) {
            this.studentsCount = studentsCount;
        }

        public String getCe() {
            return ce;
        }

        public void setCe(String ce) {
            this.ce = ce;
        }

        public String getEee() {
            return eee;
        }

        public void setEee(String eee) {
            this.eee = eee;
        }

        public String getMech() {
            return mech;
        }

        public void setMech(String mech) {
            this.mech = mech;
        }

        public String getEce() {
            return ece;
        }

        public void setEce(String ece) {
            this.ece = ece;
        }

        public String getCse() {
            return cse;
        }

        public void setCse(String cse) {
            this.cse = cse;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
