package io.can.unittestingdemo.test._02.junitBasics;

import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTestWithBasicAssertions {

    // Bu testte Junit Assertions class'inin sundugu basit assert metotlara deginilmistir.
    @Test
    @DisplayName("Test every student must have an id, name and surname")
    void shouldCreateStudentWithIdNameAndSurname() {

        Student can = new Student("1", "can", "berberoglu");

        // assertEquals -> iki degeri karsilastirir. (expected -> beklenen , actual -> asil deger)
        assertEquals("can", can.getName()); // "can".equals(can.getName()) -> ile karsilastirir.

        // assertEquals with custom message
        assertEquals("can", can.getName(), "Student name not equals");

        // assertNotEquals -> assertEquals metodunun tersi
        assertNotEquals("Can", can.getName(), "Student name equals");

        // assertTrue -> verilen actual degerinin true olup olmadigini kontrol eder. (Actual boolean expression olmalidir)
        assertTrue(can.getName().startsWith("c"));
        assertTrue(can.getName().startsWith("ca"), () -> "Student's name " + "starts with 'ca'");

        // assertFalse -> verilen actual degerinin false olup olmadigini kontrol eder. (with booleanSupplier)
        assertFalse(() -> {
            Student cem = new Student("id1", "cem", "berberoglu");
            return cem.getName().startsWith("cc");
        }, () -> "Student's name " + "starts with 'ce'");

        Student cem = new Student("2", "cem", "berberoglu");

        // assertArrayEquals -> iki array'i sirası ve icindeki degerlere gore karsilastirir.
        assertArrayEquals(new String[]{"can", "cem"}, Stream.of(can, cem).map(Student::getName).toArray());

        Student student = can;
        // assertSame -> iki referansin ayni objeyi gosterip gostermedigini kontrol eder.
        assertSame(student, can); // student == can;

        assertNotSame(student, cem);

    }

}
