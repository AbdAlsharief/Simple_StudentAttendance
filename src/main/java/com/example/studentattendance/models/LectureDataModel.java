package com.example.studentattendance.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LectureDataModel {
    private static ArrayList<Lecture> lectures = null;

    public LectureDataModel() {
        initialize();
    }

    public static void initialize() {
        if (lectures == null) {
            File file = new File("lecture.csv");
            if (file.exists()) {
                try (Scanner scanner = new Scanner(file)) {
                    // read header line
                    String s = scanner.nextLine();
                    ArrayList<Lecture> tempLectures = new ArrayList<>();
                    while (scanner.hasNext()) {
                        s = scanner.nextLine();
                        String[] strings = s.split(",");
                        tempLectures.add(new Lecture(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]),
                                strings[2], strings[3], strings[4]));
                    }
                    lectures = tempLectures; // Assign the created list to the static variable
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ArrayList<Lecture> tempLectures = new ArrayList<>();
                // add Test data when you run the program for the first time
                tempLectures.add(new Lecture(1, 120, "dfb", "java", "aa"));
                tempLectures.add(new Lecture(3, 150, "ad", "logic", "cc"));
                lectures = tempLectures; // Assign the created list to the static variable
            }
        }
    }

    public void saveLecture() {
        try (PrintWriter pw = new PrintWriter("lecture.csv")) {
            pw.println("lCode,teacher_Code,teacher_name,name,classroom");
            for (Lecture lecture : lectures) {
                pw.println(lecture.getLCode() + "," + lecture.getTeacher_Code() + "," + lecture.getTeacher_name() + ","
                        + lecture.getLName() + "," + lecture.getClassroom());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public Lecture getLectureByLName(String name) {
        for (Lecture lecture : lectures) {
            if (Objects.equals(lecture.getLName(), name)) {
                return lecture;
            }
        }
        return null;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public Lecture deleteLectureByName(String name) {
        for (Lecture lecture : lectures) {
            if (Objects.equals(lecture.getLName(), name)) {
                lectures.remove(lecture);
                return lecture;
            }
        }
        return null;
    }

    public int getLCodeByLName(String lName) {
        for (Lecture lecture : lectures) {
            if (Objects.equals(lecture.getLName(), lName)) {
                return lecture.getLCode();
            }
        }
        return -1;
    }

    public String getLNameByLCode(int code) {
        for (Lecture lecture : lectures) {
            if (lecture.getLCode() == code) {
                return lecture.getLName();
            }
        }
        return null;
    }

    public String getTeacherNameByLName(String lName) {
        for (Lecture lecture : lectures) {
            if (Objects.equals(lecture.getLName(), lName)) {
                return lecture.getTeacher_name();
            }
        }
        return null;
    }

    public void removeLecture(Lecture lecture) {
        lectures.remove(lecture);
    }
}
