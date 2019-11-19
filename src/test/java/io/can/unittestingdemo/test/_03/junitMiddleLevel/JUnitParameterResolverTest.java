package io.can.unittestingdemo.test._03.junitMiddleLevel;


import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Parameter Resolver Test Class")
public class JUnitParameterResolverTest {

    private Student student;

    // constructor'da test class bilgilerine erisebiliriz.
    // constructor -> sinifa ozel bilgiler
    public JUnitParameterResolverTest(TestInfo testInfo) {
        System.out.println("Class Display Name: " + testInfo.getDisplayName());
        System.out.println("Class Name: " + testInfo.getTestClass().get().getSimpleName());
    }

    @BeforeEach
    // test sinifina ozel bilgiler
    void setStudent(TestInfo testInfo) {
        // testInfo junit tarafindan inject edilecek.
        System.out.println("Test Display Name: " + testInfo.getDisplayName());
        System.out.println("Test method Name: " + testInfo.getTestMethod().get().getName());

        // testin tag'ine gore student'in objesi olusturuluyor. Boylece iki test'de gececek.
        System.out.println();
        if (testInfo.getTags().contains("create")) {
            student = new Student("1", "can", "berberoglu");
        } else {
            student = new Student("2", "cem", "berberoglu");
        }
    }

    @Test
    @DisplayName("Create Student")
    @Tag("create")
        // test sinifina ozel bilgiler
    void createStudent(TestInfo testInfo) {
       assertTrue(testInfo.getTags().contains("create"));
       assertEquals("can", student.getName());
    }

    @Test
    @DisplayName("Add Course to student")
    @Tag("addCourse")
    void addCourseToStudent(TestReporter testReporter) {
        testReporter.publishEntry("test start time", LocalDate.now().toString());
        assertEquals("cem", student.getName());
        testReporter.publishEntry("test end time", LocalDate.now().toString());
    }
}
