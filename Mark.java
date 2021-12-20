package app.college.smartcampus.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mark implements Serializable {

    @SerializedName("student_id")
    private String studentId;
    private String year;
    private String sem;
    @SerializedName("sub_1_int")
    private String sub1Int;
    @SerializedName("sub_2_int")
    private String sub2Int;
    @SerializedName("sub_3_int")
    private String sub3Int;
    @SerializedName("sub_4_int")
    private String sub4Int;
    @SerializedName("sub_5_int")
    private String sub5Int;
    @SerializedName("lab_1_int")
    private String lab1Int;
    @SerializedName("lab_2_int")
    private String lab2Int;
    @SerializedName("lab_3_int")
    private String lab3Int;
    @SerializedName("lab_4_int")
    private String lab4Int;
    @SerializedName("sub_1_ext")
    private String sub1Ext;
    @SerializedName("sub_2_ext")
    private String sub2Ext;
    @SerializedName("sub_3_ext")
    private String sub3Ext;
    @SerializedName("sub_4_ext")
    private String sub4Ext;
    @SerializedName("sub_5_ext")
    private String sub5Ext;
    @SerializedName("lab_1_ext")
    private String lab1Ext;
    @SerializedName("lab_2_ext")
    private String lab2Ext;
    @SerializedName("lab_3_ext")
    private String lab3Ext;
    @SerializedName("lab_4_ext")
    private String lab4Ext;
    @SerializedName("result_on")
    private String resultOn;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getSub1Int() {
        return sub1Int;
    }

    public void setSub1Int(String sub1Int) {
        this.sub1Int = sub1Int;
    }

    public String getSub2Int() {
        return sub2Int;
    }

    public void setSub2Int(String sub2Int) {
        this.sub2Int = sub2Int;
    }

    public String getSub3Int() {
        return sub3Int;
    }

    public void setSub3Int(String sub3Int) {
        this.sub3Int = sub3Int;
    }

    public String getSub4Int() {
        return sub4Int;
    }

    public void setSub4Int(String sub4Int) {
        this.sub4Int = sub4Int;
    }

    public String getSub5Int() {
        return sub5Int;
    }

    public void setSub5Int(String sub5Int) {
        this.sub5Int = sub5Int;
    }

    public String getLab1Int()
    {
        return lab1Int;
    }

    public void setLab1Int(String lab1Int) {
        this.lab1Int = lab1Int;
    }

    public String getLab2Int() {
        return lab2Int;
    }

    public void setLab2Int(String lab2Int) {
        this.lab2Int = lab2Int;
    }

    public String getLab3Int() {
        return lab3Int;
    }

    public void setLab3Int(String lab3Int) {
        this.lab3Int = lab3Int;
    }

    public String getLab4Int() {
        return lab4Int;
    }

    public void setLab4Int(String lab4Int) {
        this.lab4Int = lab4Int;
    }

    public String getSub1Ext() {
        return sub1Ext;
    }

    public void setSub1Ext(String sub1Ext) {
        this.sub1Ext = sub1Ext;
    }

    public String getSub2Ext() {
        return sub2Ext;
    }

    public void setSub2Ext(String sub2Ext) {
        this.sub2Ext = sub2Ext;
    }

    public String getSub3Ext() {
        return sub3Ext;
    }

    public void setSub3Ext(String sub3Ext) {
        this.sub3Ext = sub3Ext;
    }

    public String getSub4Ext() {
        return sub4Ext;
    }

    public void setSub4Ext(String sub4Ext) {
        this.sub4Ext = sub4Ext;
    }

    public String getSub5Ext() {
        return sub5Ext;
    }

    public void setSub5Ext(String sub5Ext) {
        this.sub5Ext = sub5Ext;
    }

    public String getLab1Ext() {
        return lab1Ext;
    }

    public void setLab1Ext(String lab1Ext) {
        this.lab1Ext = lab1Ext;
    }

    public String getLab2Ext() {
        return lab2Ext;
    }

    public void setLab2Ext(String lab2Ext) {
        this.lab2Ext = lab2Ext;
    }

    public String getLab3Ext() {
        return lab3Ext;
    }

    public void setLab3Ext(String lab3Ext) {
        this.lab3Ext = lab3Ext;
    }

    public String getLab4Ext() {
        return lab3Ext;
    }

    public void setLab4Ext(String lab4Ext) {
        this.lab4Ext = lab4Ext;
    }

    public String getResultOn() {
        return resultOn;
    }

    public void setResultOn(String resultOn) {
        this.resultOn = resultOn;
    }
}
