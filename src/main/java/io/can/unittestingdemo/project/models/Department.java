package io.can.unittestingdemo.project.models;

import java.util.Set;

public class Department {

    private String code;
    private String name;
    private Set<Lecturer> lecturers; // OneToMany -> hangi ders verenler bu bolume bagli
    private Set<Course> courses; // OneToMany -> hangi dersler bu bolume bagli
    private Set<Student> students; // OneToMany -> hangi ogrenciler bu bolume bagli
    private Faculty faculty; // ManyToOne -> hangi fakulteye bagli
}
