package io.can.unittestingdemo.test._03.junitMiddleLevel;

import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithTestLifecycle {

    // PER_METHOD oldugunda her test metodu icin test class'i olusturulacagi icin
    // asagidaki field'in degeri hic bir test metodunda degismeyecektir.
    private Student student = new Student("1", "can", "berberoglu");

    // PER_CLASS olursa asagidaki testlerden birisi fail olacaktir. Junit testlerin calisma sirasina
    // gore garanti vermemektedir.

    // PER_METHOD icin testlerin ikisi de gececektir.

    @Test
    void stateCannotChangeWhenLifecycleIsPerMethod() {
        assertEquals("can", student.getName());
        student = new Student("2", "cem", "berberoglu");
    }

    @Test
    void stateCannotChangeWhenLifecycleIsPerClass() {
        assertEquals("can", student.getName());
        student = new Student("2", "cem", "berberoglu");
    }
}
