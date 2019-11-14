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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
