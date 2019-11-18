package io.can.unittestingdemo.project.models;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private final String id;
    private final String name;
    private final String surname;
    private Set<StudentCourseRecord> studentCourseRecords = new HashSet<>(); // OneToMany -> hangi dersleri aliyor
    private Department department; // ManyToOne -> hangi bolume bagli

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void addCourse(LecturerCourseRecord lecturerCourseRecord) {

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lecturerCourseRecord == null) {
            throw new IllegalArgumentException("Can't add course with null lecturer course record");
        }

        final StudentCourseRecord studentCourseRecord = new StudentCourseRecord(lecturerCourseRecord);
        studentCourseRecords.add(studentCourseRecord);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<StudentCourseRecord> getStudentCourseRecords() {
        return studentCourseRecords;
    }
}
