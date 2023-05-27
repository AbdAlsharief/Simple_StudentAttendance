package com.example.studentattendance;


import com.example.studentattendance.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public  class DataModel {


    public static class AccountDataModel {
        private static ArrayList<Account> accounts = null;

        public AccountDataModel() {
            initialize();
        }

        public static void initialize() {
            if (accounts == null) {
                File file = new File("account.csv");
                if (file.exists()) {
                    try (Scanner scanner = new Scanner(file)) {
                        // read header line
                        String s = scanner.nextLine();
                        accounts = new ArrayList<>();
                        while (scanner.hasNext()) {
                            s = scanner.nextLine();
                            String[] strings = s.split(",");
                            accounts.add(new Account(
                                    Integer.parseInt(strings[0]),
                                    strings[1],
                                    strings[2]
                            ));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    accounts = new ArrayList<>();
                    // add Test data when you run the program for the first time
                    accounts.add(new Account(1, "admin", "admin"));
                    accounts.add(new Account(2, "admin2", "admin2"));
                    accounts.add(new Account(150, "teacher", "teacher"));
                    accounts.add(new Account(140, "teacher", "teacher"));
                }
            }
        }

        public void saveAccounts() {
            try (PrintWriter pw = new PrintWriter("account.csv")) {
                pw.println("code,username,password");
                for (Account account : accounts) {
                    pw.println(account.getCode() + "," + account.getUsername() + "," + account.getPassword());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void addAccount(Account account) {
            accounts.add(account);
        }

        public Account getAccountByUsername(String username) {
            for (Account account : accounts) {
                if (Objects.equals(account.getUsername(), username)) {
                    return account;
                }
            }
            return null;
        }

        public ArrayList<Account> getAccounts() {
            return accounts;
        }

        public Account deleteAccountByUsername(String username) {
            for (Account account : accounts) {
                if (Objects.equals(account.getUsername(), username)) {
                    accounts.remove(account);
                    return account;
                }
            }
            return null;
        }

        public boolean isAdmin(Account account) {
            return account.getCode() >= 0 && account.getCode() < 100;
        }

        public void updateAccountCode(Account account) {
            if (isAdmin(account)) {
                account.setCode((int) (Math.random() * 100));
            } else {
                account.setCode((int) (Math.random() * 100) + 100);
            }
        }

        public  String getUsernameByCode(int code) {
            for (Account account : accounts) {
                if (account.getCode() >= 100 && account.getCode() < 200 && account.getCode() == code) {
                    return account.getUsername();
                }
            }
            return null;
        }

        public int getCodeByUsername(String username) {
            for (Account account : accounts) {
                if (account.getUsername().equals(username)) {
                    return account.getCode();
                }
            }
            return -1;
        }

        public void setUsernameByCode(int code, String newUsername) {
            for (Account account : accounts) {
                if (account.getCode() == code) {
                    account.setUsername(newUsername);
                    break;
                }
            }
        }

        public void removeAccount(Account account) {
            accounts.remove(account);
        }
    }

    public static class LectureDataModel {
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

    public static class StudentDataModel {
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

       public void setNameById(int id, String name) {
            for (Student student : students) {
                if (student.getID() == id) {
                    student.setStudentName(name);
                    break;
                }
           }
        }

        public void removeStudent(Student student) {
            students.remove(student);
        }

        public boolean isIDUnique(int id) {
            for (Student student : students) {
                if (student.getID() == id) {
                    return false; // ID already exists
                }
            }
            return true; // ID is unique
        }
        public int getNextUniqueID(int minId, int maxId) {
            int id = minId;
            boolean isUnique = false;

            while (!isUnique) {
                if (id > maxId) {
                    // Reset ID range if it exceeds the maximum
                    id = minId;
                }

                if (isIDUnique(id)) {
                    isUnique = true;
                } else {
                    id++;
                }
            }

            return id;
        }


    }

    public static class Teacher_StudentDataModel {

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
}
