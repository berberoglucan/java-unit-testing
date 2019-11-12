package io.can.unittestingdemo.project.models;

import java.util.Set;

public class Lecturer {

    private String id;
    private String name;
    private String surname;
    private String title;
    private Set<LecturerCourseRecord> lecturerCourseRecords; // OneToMany -> hangi dersleri veriyor
    private Department department; // ManyToOne -> hangi bolume bagli

}
