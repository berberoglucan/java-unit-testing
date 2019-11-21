package io.can.unittestingdemo.project.models;

import io.can.unittestingdemo.project.enums.CourseReview;
import io.can.unittestingdemo.project.enums.Grade;

public class StudentCourseRecord {

    private final LecturerCourseRecord lecturerCourseRecord; // -> hangi ogretim uyesinden bu dersi almis
    private Grade grade;
    private CourseReview courseReview;
    private Student student; // ManyToOne -> ogrencinin kendi bilgisi

    public StudentCourseRecord(LecturerCourseRecord lecturerCourseRecord) {
        this.lecturerCourseRecord = lecturerCourseRecord;
    }

    public LecturerCourseRecord getLecturerCourseRecord() {
        return lecturerCourseRecord;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public CourseReview getCourseReview() {
        return courseReview;
    }

    public void setCourseReview(CourseReview courseReview) {
        this.courseReview = courseReview;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
