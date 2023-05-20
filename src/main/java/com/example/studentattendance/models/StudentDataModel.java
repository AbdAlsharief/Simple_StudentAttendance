package com.example.studentattendance.models;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class StudentDataModel {
    private static ArrayList<Student> students = null;
    Person person= new Person();

    public StudentDataModel() {
        initialize();
    }
    public static void initialize() {
        if (students == null) {
            File file = new File("student.csv");
            if (file.exists()) {
                try (Scanner scanner = new Scanner(file)) {
                    // read header line
                    String s = scanner.nextLine();
                    students = new ArrayList<>();
                    while (scanner.hasNext()) {
                        s = scanner.nextLine();
                        String[] strings = s.split(",");
                        students.add(new Student(Integer.parseInt(strings[0]), strings[1],strings[2],strings[3]));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                students = new ArrayList<>();
                // add Test data when you run the program for the first time
                students.add(new Student(1, "Student1","05932","adw" ));
                students.add(new Student(2, "Student2","05942","awd" ));

            }
        }
    }

    public void saveStudent() {
        try (PrintWriter pw = new PrintWriter("student.csv")) {
            pw.println("id,studentName,mobileNumber,residenceArea");
            for (Student student : students) {
                pw.println(student.getID() + "," + student.getStudentName() + "," + student.getMobileNumber()+","+student.getResidenceArea());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (Objects.equals(student.getStudentName(), name)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student deleteAccountByName(String name) {
        for (Student student : students) {
            if (Objects.equals(student.getStudentName(), name)) {
                students.remove(student);
                return student;
            }
        }
        return null;
    }

    public  String getNameById(int id) {
        for (Student student : students) {
            if ( student.getID() == id) {
                return student.getStudentName();
            }
        }
        return null;
    }

    public int getIdByName(String name) {
        for (Student student : students) {
            if (student.getStudentName().equals(name)) {
                return student.getID();
            }
        }
        return -1;
    }

//    public void setUsernameByCode(int code, String newUsername) {
//        for (Account account : accounts) {
//            if (account.getCode() == code) {
//                account.setUsername(newUsername);
//                break;
//            }
//        }
//    }

    public void removeStudent(ObservableList<Student> student) {
        students.remove(student);
    }
}
