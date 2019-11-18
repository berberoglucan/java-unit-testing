package io.can.unittestingdemo.project.models;

import io.can.unittestingdemo.project.exception.NotActiveSemesterException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Lecturer {

    private String id;
    private String name;
    private String surname;
    private String title;
    private Set<LecturerCourseRecord> lecturerCourseRecords = new HashSet<>(); // OneToMany -> hangi dersleri veriyor
    private Department department; // ManyToOne -> hangi bolume bagli

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitle() {
        return title;
    }

    public Set<LecturerCourseRecord> getLecturerCourseRecords() {
        return Collections.unmodifiableSet(this.lecturerCourseRecords);
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addLecturerCourseRecord(LecturerCourseRecord lecturerCourse) {
        if (lecturerCourse == null || lecturerCourse.getCourse() == null) {
            throw new IllegalArgumentException("Course is not be null");
        }

        if (!lecturerCourse.getSemester().isActive()) {
            throw new NotActiveSemesterException("not active semester");
        }

        lecturerCourseRecords.add(lecturerCourse);
        lecturerCourse.setLecturer(this);
    }
}
