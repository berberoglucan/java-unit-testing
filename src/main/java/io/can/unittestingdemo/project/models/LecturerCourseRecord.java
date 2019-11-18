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

    public Course getCourse() {
        return course;
    }

    public int getCredit() {
        return credit;
    }

    public Semester getSemester() {
        return semester;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
