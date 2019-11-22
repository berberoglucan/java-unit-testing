package io.can.unittestingdemo.test._03.junitMiddleLevel;

import io.can.unittestingdemo.project.enums.CourseType;
import io.can.unittestingdemo.project.models.Course;
import io.can.unittestingdemo.project.models.LecturerCourseRecord;
import io.can.unittestingdemo.project.models.Semester;
import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class StudentTestWithParameterizedTest {

    private Student student;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("ValueSourceDemo")
    class ValueSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "can","berberoglu");
        }

        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"}) // Her string degeri icin testi tek tek calistiracaktir. (3 kez)
        void addCourseToStudent(String courseCode) {
            final LecturerCourseRecord courseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("EnumSourceDemo")
    class EnumSourceDemo {

        @BeforeAll
        void setUp() {
            student = new Student("1", "can","berberoglu");
        }

        @ParameterizedTest
        @EnumSource(value = CourseType.class) // enum'un tüm degerleri icin bu testi tek tek calistirir. (MANDATORY, ELECTIVE)
        void addCourseToStudent(CourseType courseType) {

            String courseCode = String.valueOf(new Random().nextInt(200));
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @EnumSource(value = CourseType.class, names = {"MANDATORY"}) // Sadece MANDATORY icin 1 kez calisacak
        void addMandatoryCourseToStudent(CourseType courseType) {
            String courseCode = String.valueOf(new Random().nextInt(200));
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(CourseType.MANDATORY, course.getCourseType());
        }

        @ParameterizedTest
        @EnumSource(value = CourseType.class, names = {"MANDATORY"}, mode = EnumSource.Mode.EXCLUDE) // Mandatory olmayan tüm parametreler icin calistirir.
        void addNotMandatoryCourseToStudent(CourseType courseType) {
            String courseCode = String.valueOf(new Random().nextInt(200));
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertNotEquals(CourseType.MANDATORY, course.getCourseType());
            System.out.println(student.getStudentCourseRecords().size());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("MethodSourceDemo")
    class MethodSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "can","berberoglu");
        }

        // Factory method
        Stream<String> generateCourseCode() {
            return Stream.of("101", "103", "105");
        }

        @ParameterizedTest
        @MethodSource(value = {"generateCourseCode"})
        void addCourseToStudent(String courseCode) {
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(courseRecord.getCourse()));
        }

        // factory method
        // junit'in Arguments interface'i ile birden fazla argument'de gecilebilir.
        Stream<Arguments> generateCourseCodeAndCourseType() {
            return Stream.of(
                    Arguments.of("101", CourseType.MANDATORY),
                    Arguments.of("103", CourseType.MANDATORY),
                    Arguments.of("105", CourseType.ELECTIVE)
            );
        }

        @ParameterizedTest
        @MethodSource(value = {"generateCourseCodeAndCourseType"})
        void addCourseToStudentWithArguments(String courseCode, CourseType courseType) {
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(new Course(courseCode, courseType), new Semester());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(courseRecord.getCourse()));
            // assertEquals(courseType, courseRecord.getCourse().getCourseType());
            assumingThat(courseCode.equals("101"),
                    () -> assertEquals(CourseType.MANDATORY, courseType));

            assumingThat(courseCode.equals("103"),
                    () -> assertEquals(CourseType.MANDATORY, courseType));

            assumingThat(courseCode.equals("105"),
                    () -> assertEquals(CourseType.ELECTIVE, courseType));
        }

    }


    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("CsvSourceDemo")
    class CsvSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("1", "can","berberoglu");
        }

        @ParameterizedTest
        // Junit otomatik olarak string degerleri enum ile eslestirmekte ve convert etmektedir.
        @CsvSource(value = {"101,MANDATORY","103,ELECTIVE", "105,MANDATORY"}) // 101 -> courseCode, MANDATORY-> courseType olarak parametre verilecek
        // ve 3 kere calisacak
        void addCourseToStudentWithArguments(String courseCode, CourseType courseType) {
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(new Course(courseCode, courseType), new Semester());
            student.addCourse(courseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(courseRecord.getCourse()));
            assumingThat(courseCode.equals("101"),
                    () -> assertEquals(CourseType.MANDATORY, courseType));

            assumingThat(courseCode.equals("103"),
                    () -> assertEquals(CourseType.ELECTIVE, courseType));

            assumingThat(courseCode.equals("105"),
                    () -> assertEquals(CourseType.MANDATORY, courseType));
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("TypeConversion")
    class TypeConversionDemo {

        @BeforeAll
        void setUp() {
            student = new Student("1", "can","berberoglu");
        }

        // enum conversion
        @ParameterizedTest
        // Junit string degerleri otomatik olarak CourseType tipine convert edecektir.
        // Junit string degerleri cogu sinifa convert edebilmektedir. User guide'dan bakilabilir
        @ValueSource(strings = {"MANDATORY", "ELECTIVE"})
        @DisplayName("Auto enum conversion")
        void addCourseToStudentWithEnumConversion(CourseType courseType) {
            String courseCode = String.valueOf(new Random().nextInt(200));
            Course course = new Course(courseCode, courseType);
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // Factory method or constructor conversation
        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"}) // Course icerisinde courseCode alan constructor oldugu icin Junit
        // bu constructor'i kullanip objeyi bize verecektir.
        // Constructor olmasaydi course icerisinde bulunan factory metodunu bulup calistiracakti.
        @DisplayName("Factory method or constructor conversation")
        void addCourseToStudentWithFactoryOrConstructorConversation(Course course) {
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // conversion using SimpleConverter with @ConvertWith -> kendi converter class'imizi yazabiliriz.

        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"})
        @DisplayName("conversion using SimpleConverter with @ConvertWith")
        // CourseConverter static class'ini kullanacaktir.
        void addCourseToStudentWithSimpleConverterClass(@ConvertWith(CourseConverter.class) Course course) {
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // conversation with @JavaTimeConversationPattern -> verilen string formatindaki tarihi LocalDate veya LocalTime
        // convert edebiliriz.
        @ParameterizedTest(name = "{index} --> first: {0}") // ozel isimlendirme
        @ValueSource(strings = {"19.01.1995", "27.07.1995"})
        @DisplayName("conversation with @JavaTimeConversationPattern")
        void addCourseToStudentWithLocalDateTime(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate date) {
            Course course = Course.newCourse(String.valueOf(new Random().nextInt(200)));
            LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester(date));
            student.addCourse(courseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }


    }

    // Course converter class
    static class CourseConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            return Course.newCourse((String) source);
        }
    }

}
