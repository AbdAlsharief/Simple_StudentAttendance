package com.example.studentattendance.models;

import java.util.ArrayList;

public class Student extends Person {
    private int ID;
    private String mobileNumber;
    private String residenceArea;
    private ArrayList<String> courses;

    public Student( int ID,String name, String mobileNumber, String residenceArea) {
        super(name,mobileNumber,residenceArea);
        this.ID = ID;
        this.mobileNumber = mobileNumber;
        this.residenceArea = residenceArea;
        this.courses = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getResidenceArea() {
        return residenceArea;
    }

    public void setResidenceArea(String residenceArea) {
        this.residenceArea = residenceArea;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public void removeCourse(String course) {
        courses.remove(course);
    }

    public boolean isTakingCourse(String course) {
        return courses.contains(course);
    }
}
