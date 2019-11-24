package io.can.unittestingdemo.project.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {

    private final String id;
    private final String name;
    private final String surname;
    private LocalDate dob;
    private Set<StudentCourseRecord> studentCourseRecords = new HashSet<>(); // OneToMany -> hangi dersleri aliyor
    private Department department; // ManyToOne -> hangi bolume bagli

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Student(String id, String name, String surname, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
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

    public LocalDate getDob() {
        return dob;
    }

    public Set<StudentCourseRecord> getStudentCourseRecords() {
        return studentCourseRecords;
    }

    public boolean isTakeCourse(Course course) {
        return studentCourseRecords.stream()
                .map(StudentCourseRecord::getLecturerCourseRecord)
                .map(LecturerCourseRecord::getCourse)
                .anyMatch(course1 -> course1.equals(course));
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", studentCourseRecords=" + studentCourseRecords +
                ", department=" + department +
                '}';
    }
}
