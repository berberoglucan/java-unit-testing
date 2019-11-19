package io.can.unittestingdemo.test._03.junitMiddleLevel;

import io.can.unittestingdemo.project.models.Course;
import io.can.unittestingdemo.project.models.LecturerCourseRecord;
import io.can.unittestingdemo.project.models.Semester;
import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithRepeatedTest implements TestLifecycleReporter{

    private Student student;
    
    @BeforeAll
    void setUp() {
        student = new Student("1", "can", "berberoglu");
    }

    @DisplayName("Add course to student")
    @RepeatedTest(value = 5, name = "{displayName} => Add one course to student has {currentRepetition} courses")
        // 5 kez ard arda calisacak
    void addCourseToStudent(RepetitionInfo repetitionInfo) {
        LecturerCourseRecord courseRecord =
                new LecturerCourseRecord(new Course(String.valueOf(repetitionInfo.getCurrentRepetition())), new Semester());
        student.addCourse(courseRecord);
        Assertions.assertEquals(repetitionInfo.getCurrentRepetition(), student.getStudentCourseRecords().size());
    }
}
