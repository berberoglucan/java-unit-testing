package io.can.unittestingdemo.test._05.assertj;

import io.can.unittestingdemo.project.models.Student;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

// Student icin assertJ'de custom assertion'lar olusturabiliriz.
public class CustomStudentAssert extends AbstractAssert<CustomStudentAssert, Student> {

    public CustomStudentAssert(Student student) {
        super(student, CustomStudentAssert.class);
    }

    public static CustomStudentAssert assertThat(Student actual) {
        return new CustomStudentAssert(actual);
    }

    public CustomStudentAssert hasName(String name) {
        isNotNull();
        if (!Objects.equals(name, actual.getName())) {
            failWithMessage("Expected student's name %s but was found %s",
                    name, actual.getName());
        }
        return this;
    }

}
