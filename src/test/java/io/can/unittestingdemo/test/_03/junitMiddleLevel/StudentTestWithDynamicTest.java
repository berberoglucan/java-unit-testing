package io.can.unittestingdemo.test._03.junitMiddleLevel;

import io.can.unittestingdemo.project.enums.CourseType;
import io.can.unittestingdemo.project.models.Course;
import io.can.unittestingdemo.project.models.LecturerCourseRecord;
import io.can.unittestingdemo.project.models.Semester;
import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;

public class StudentTestWithDynamicTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("1", "can", "berberoglu");

    }


    @TestFactory
    Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType() {
        // her courseCode icin ayri ayri courseType ile denenmesi. 6 farkli test olusturup calistiracaktir.
        return Stream.of("101", "103", "105")
                // container
                .map(courseCode -> dynamicContainer("Add course <" + courseCode + "> to student",
                        Stream.of(CourseType.MANDATORY, CourseType.ELECTIVE).map(courseType -> dynamicTest("Add course <" + courseType + "> to student",
                                () -> {
                                    // asil test
                                    final Course course = new Course(courseCode, courseType);
                                    LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
                                    student.addCourse(courseRecord);
                                    Assertions.assertTrue(student.isTakeCourse(course));
                                }))));
    }

    // farkli yol
    @TestFactory
    Stream<DynamicTest> addCourseToStudentWithCourseCode() {

        // parameters
        Iterator<String> courseCodeGenerator = Stream.of("101", "103", "105").iterator();

        // displayName
        Function<String, String> displayNameGenerator = courseCode -> "Add course <" + courseCode + "> to student";

        // executable test
        ThrowingConsumer<String> testExecutor = courseCode -> {
            final Course course = new Course(courseCode);
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            Assertions.assertTrue(student.isTakeCourse(course));
        };

        return DynamicTest.stream(courseCodeGenerator, displayNameGenerator, testExecutor);
    }

}
