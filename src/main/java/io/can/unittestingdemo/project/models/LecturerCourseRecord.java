package io.can.unittestingdemo.project.models;

public class LecturerCourseRecord {

    private final Course course; // hangi ders
    private int credit;
    private final Semester semester; // bu ders hangi donemde veriliyor
    private Lecturer lecturer; // ManyToOne -> dersi kim veriyor

    public LecturerCourseRecord(Course course, Semester semester) {
        this.course = course;
        this.semester = semester;
    }
}
