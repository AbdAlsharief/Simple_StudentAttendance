package com.example.studentattendance.models;

public class Teacher extends Person {
    private String subject;
    private String book;
    private String hall;

    public Teacher(String name, String mobileNumber, String residenceArea, String subject, String book, String hall) {
        super(name, mobileNumber, residenceArea);
        this.subject = subject;
        this.book = book;
        this.hall = hall;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String virtualMeetingPlace) {
        this.hall = virtualMeetingPlace;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSubject: " + subject +
                "\nBook: " + book +
                "\nVirtual Meeting Place: " + hall;
    }
}