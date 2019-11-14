package io.can.unittestingdemo.project.models;

public class LecturerCourseRecord {

    private Course course; // hangi ders
    private int credit;
    private Semester semester; // bu ders hangi donemde veriliyor
    private Lecturer lecturer; // ManyToOne -> dersi kim veriyor

    public LecturerCourseRecord() {
    }

    public LecturerCourseRecord(Course course, Semester semester) {
        this.course = course;
        this.semester = semester;
    }
}
