package io.can.unittestingdemo.test._03.junitMiddleLevel;

import io.can.unittestingdemo.project.models.LecturerCourseRecord;
import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTestWithNestedTest {

    @Nested
    @DisplayName("Create Student")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS) // nested'in kendi lifecycle'i olabilir
    class CreateStudent {

        @Test
        @DisplayName("Test every student must have an id, name and surname")
        void shouldCreateStudentWithIdNameAndSurname() {

            Student can = new Student("1", "can", "berberoglu");
            assertEquals("can", can.getName(), "Student name not equals");

            assertTrue(can.getName().startsWith("c"));

            assertFalse(() -> {
                Student cem = new Student("id1", "cem", "berberoglu");
                return cem.getName().startsWith("cc");
            }, () -> "Student's name " + "starts with 'ce'");

            Student cem = new Student("2", "cem", "berberoglu");

            // assertArrayEquals -> iki array'i sirası ve icindeki degerlere gore karsilastirir.
            assertArrayEquals(new String[]{"can", "cem"}, Stream.of(can, cem).map(Student::getName).toArray());

        }

        @Test
        @DisplayName("Test every student must have an id, name and surname with groupped asssertions")
        void shouldCreateStudentWithIdNameAndSurnameGroupedAssertions() {


            Student student = new Student("1", "can", "berberoglu");
            assertAll("Student's name check",
                    () -> assertEquals("can", student.getName()),
                    () -> assertEquals("can", student.getName(), "Student's name"),
                    () -> assertNotEquals("cem", student.getName(), "Student's name"));

            assertAll("Student's name character check",
                    () -> assertTrue(student.getName().startsWith("ca")),
                    () -> assertTrue(student.getName().startsWith("ca"), () -> "Student's name " + "starts with 'ca'"),
                    () -> assertFalse(() -> {
                        Student student2 = new Student("2", "cem", "berberoglu");
                        return student2.getName().startsWith("ca");
                    }, () -> "Student's name " + "starts with 'ce'"));
        }
    }

    @Nested // nested test
    @DisplayName("add courseToStudent")
    class AddCourseToStudent {

        @Test
        @DisplayName("Add course to a student less than 10ms")
        void addCourseToStudentWithATimeConstraint() {

            String result = assertTimeout(Duration.ofMillis(10L), () -> {
                return "some string result";
            });
            assertEquals("some string result", result);

            Student student = new Student("1", "can", "berberoglu");
            LecturerCourseRecord course = new LecturerCourseRecord();
            assertTimeout(Duration.ofMillis(200L), () -> student.addCourse(course));
        }

        @Nested // nested icinde nested test
        @DisplayName("add null course to student")
        class AddNullCourseToStudent {
            @Test
            @DisplayName("Got an exception when a null lecturer course record to student")
            void throwsExceptionWhenAddToNullCourseToStudent() {
                Student student = new Student("1", "can", "berberoglu");
                assertThrows(IllegalArgumentException.class, () -> student.addCourse(null), "throws IllegalArgumentException");
                IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> student.addCourse(null));
                assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
            }
        }

    }
}
