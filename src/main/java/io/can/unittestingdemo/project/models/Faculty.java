package io.can.unittestingdemo.project.models;

import java.util.Set;

public class Faculty {

    private String code;
    private String name;
    private Set<Department> departments; // OneToMany -> kendisine bagli bolumler
}
