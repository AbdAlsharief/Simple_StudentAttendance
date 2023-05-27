package com.example.studentattendance.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher_StudentDataModel {

    private static ArrayList<Teacher_Student> teacher_students = null;

    public Teacher_StudentDataModel() {
        initialize();
    }

    public static void initialize() {
        if (teacher_students == null) {
            File file = new File("teacher_Student.csv");
            if (file.exists()) {
                try (Scanner scanner = new Scanner(file)) {
                    // read header line
                    String s = scanner.nextLine();
                    teacher_students = new ArrayList<>();
                    while (scanner.hasNext()) {
                        s = scanner.nextLine();
                        String[] strings = s.split(",");
                        teacher_students.add(new Teacher_Student(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]), strings[3], Integer.parseInt(strings[4]), strings[5], Integer.parseInt(strings[6])));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                teacher_students = new ArrayList<>();
                // add Test data when you run the program for the first time
                teacher_students.add(new Teacher_Student(10002, "Student1", 208, "teacher1", 1, "", 25));
                teacher_students.add(new Teacher_Student(20000, "Student2", 210, "teacher2", 3, "", 50));
            }
        }
    }

    public void saveTeacher_Student() {
        try (PrintWriter pw = new PrintWriter("teacher_Student.csv")) {
            pw.println("student_ID,student_name,teacher_Code,teacher_name,l_Code,l_Name,attendance");
            for (Teacher_Student teacher_student : teacher_students) {
                pw.println(teacher_student.getStudent_ID() + "," + teacher_student.getStudent_name() + "," + teacher_student.getTeacher_Code() + "," + teacher_student.getTeacher_name() + "," + teacher_student.getL_Code() + "," + teacher_student.getL_Name() + "," + teacher_student.getAttendance());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTeacher_Student(Teacher_Student teacher_student) {
        teacher_students.add(teacher_student);
    }

    public ArrayList<Teacher_Student> getTeacher_students() {
        return teacher_students;
    }

    public void removeTeacher_Student(Teacher_Student teacher_student) {
        teacher_students.remove(teacher_student);
    }

    public void updateTeacher_Student(Teacher_Student updatedTeacherStudent) {
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_ID() == updatedTeacherStudent.getStudent_ID()) {
                teacherStudent.setAttendance(updatedTeacherStudent.getAttendance());
                // You can update other fields as well if needed
                break;
            }
        }
    }

    public int getAttendanceByIdAndLecture(int studentID, String lectureName) {
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_ID() == studentID && teacherStudent.getL_Name().equals(lectureName)) {
                return teacherStudent.getAttendance();
            }
        }
        return -1; // Return -1 if the attendance is not found
    }

    public void updateAttendanceByIdAndLecture(int studentID, String lectureName, int newAttendance) {
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_ID() == studentID && teacherStudent.getL_Name().equals(lectureName)) {
                teacherStudent.setAttendance(newAttendance);
                break;
            }
        }
    }

    public void checkAndUpdateAttendance() {
        int maxAttendance = -1;
        Teacher_Student studentWithMaxAttendance = null;

        // Find the student with the highest attendance
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getAttendance() > maxAttendance) {
                maxAttendance = teacherStudent.getAttendance();
                studentWithMaxAttendance = teacherStudent;
            }
        }

        // Remove students with a difference in attendance more than 25 from the student with the highest attendance
        if (studentWithMaxAttendance != null) {
            for (Teacher_Student teacherStudent : teacher_students) {
                if (teacherStudent != studentWithMaxAttendance) {
                    int attendanceDifference = Math.abs(studentWithMaxAttendance.getAttendance() - teacherStudent.getAttendance());
                    if (attendanceDifference > 25) {
                        teacher_students.remove(teacherStudent);
                    }
                }
            }
        }
    }

    public ArrayList<Teacher_Student> getTeacher_StudentsByLectureName(String lectureName) {
        ArrayList<Teacher_Student> matchingStudents = new ArrayList<>();
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getL_Name().equals(lectureName)) {
                matchingStudents.add(teacherStudent);
            }
        }
        return matchingStudents;
    }

    public ArrayList<Teacher_Student> getTeacher_StudentsByTeacherName(String teacherName) {
        ArrayList<Teacher_Student> matchingStudents = new ArrayList<>();
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getTeacher_name().equalsIgnoreCase(teacherName)) {
                matchingStudents.add(teacherStudent);
            }
        }
        return matchingStudents;
    }

    public void takeAttendance(int studentID) {
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_ID() == studentID) {
                int currentAttendance = teacherStudent.getAttendance();
                teacherStudent.setAttendance(currentAttendance + 1);
                break;
            }
        }
    }

    public Teacher_Student getTeacher_StudentByName(String name) {
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_name().equalsIgnoreCase(name)) {
                return teacherStudent;
            }
        }
        return null; // Return null if the student with the given name is not found
    }

    public Teacher_Student getTeacher_StudentById(int studentID) {
        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_ID() == studentID) {
                return teacherStudent;
            }
        }
        return null; // Return null if the student with the given ID is not found
    }
    public ArrayList<Teacher_Student> getLectureAndAttendanceDetails(Teacher_Student student) {
        ArrayList<Teacher_Student> studentDetails = new ArrayList<>();

        for (Teacher_Student teacherStudent : teacher_students) {
            if (teacherStudent.getStudent_ID() == student.getStudent_ID()) {
                studentDetails.add(teacherStudent);
            }
        }

        return studentDetails;
    }
}
