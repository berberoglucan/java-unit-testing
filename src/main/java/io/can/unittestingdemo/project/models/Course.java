package io.can.unittestingdemo.project.models;

import io.can.unittestingdemo.project.enums.CourseType;

public class Course {

    private String code;
    private String name;
    private String description;
    private CourseType courseType;
    private int credit;
    private Department department; // ManyToOne -> hangi bolume bagli

}
