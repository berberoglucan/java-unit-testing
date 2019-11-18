package io.can.unittestingdemo.test._02.junitBasics;

import io.can.unittestingdemo.project.exception.NotActiveSemesterException;
import io.can.unittestingdemo.project.models.Course;
import io.can.unittestingdemo.project.models.Lecturer;
import io.can.unittestingdemo.project.models.LecturerCourseRecord;
import io.can.unittestingdemo.project.models.Semester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Pratik2AddCourseToLecturer {

    // 1) When a lecturer course record is added to lecturer then lecturer course size is increased

    // 2) Lecturer course record has lecturer when added to lecturer

    // 3) Throws illegal argument exception when a null course is added to lecturer

    // 4) Throws not active semester exception when a course is added for not active semester

    private Lecturer lecturer;
    private LecturerCourseRecord course;

    @BeforeEach
    void init() {
        lecturer = new Lecturer();
        course = new LecturerCourseRecord(new Course(), new Semester());
    }

    @Test
    @DisplayName("1) When a lecturer course record is added to lecturer then lecturer course size is increased")
    void whenACourseAddedToLecturerThenLecturerCourseSizeIncrease() {
        assertEquals(0 , lecturer.getLecturerCourseRecords().size());
        lecturer.addLecturerCourseRecord(course);
        assertEquals(1, lecturer.getLecturerCourseRecords().size());
    }

    @Test
    @DisplayName("2) Lecturer course record has lecturer when added to lecturer")
    void lecturerCourseRecordHasLecturerWhenAddedToLecturer() {
        lecturer.addLecturerCourseRecord(course);
        assertEquals(lecturer, course.getLecturer()); // objelerin ayni adreste olup olmadigini kontrol eder.
    }

    @Test
    @DisplayName("3) Throws illegal argument exception when a null course is added to lecturer")
    void throwsIllegalArgumentExceptionWhenANullCourseIsAddedToLecturer() {
        assertThrows(IllegalArgumentException.class, () -> lecturer.addLecturerCourseRecord(null));
        course.setCourse(null);
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> lecturer.addLecturerCourseRecord(course));
        assertEquals("Course is not be null", exc.getMessage());
    }

    @Test
    @DisplayName("4) Throws not active semester exception when a course is added for not active semester")
    void throwsNotActiveSemesterExceptionWhenACourseIsAddedForNotActiveSemester() {

        Semester activeSemester = new Semester();
        LocalDate lastYear = LocalDate.of(activeSemester.getYear() - 1, 1, 1);
        Semester notActiveSemester = new Semester(lastYear);
        course.setSemester(notActiveSemester); // init icerisinde olusturuluyor. Bu yüzden sadece set yaptik.
        NotActiveSemesterException exception = assertThrows(NotActiveSemesterException.class, () -> lecturer.addLecturerCourseRecord(course));
        assertEquals("not active semester", exception.getMessage());

    }

}
