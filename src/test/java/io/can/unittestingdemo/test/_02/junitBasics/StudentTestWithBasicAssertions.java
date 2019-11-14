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

    @Test
    @DisplayName("Test every student must have an id, name and surname with groupped asssertions")
    void shouldCreateStudentWithIdNameAndSurnameGroupedAssertions() {

        // assertAll -> assertion'larin gruplanmasini ve grup halinde calistirilmasini saglar.
        // Parametre olarak Executable(functional interface) alir. Bu parametre herhangi calistiralacak bir kod parcasidir.
        // Assertion'lari icerisine tek tek, collection olarak veya stream olarak verebiliriz.

        // Assertion'larin organize edilmesini saglar. Tek bir assertion fail olsa bile hepsini calistirmaya devam eder.
        // Bir grup icerisindeki tum fail olan assertion'lari gosterebilmektedir.

        Student student = new Student("1", "can", "berberoglu");

        // Gruplanmis assertion'larin hepsi calisacaktir.
        assertAll("Student's name check",
                () -> assertEquals("can", student.getName()),
                () -> assertEquals("can", student.getName(), "Student's name"),
                () -> assertNotEquals("cem", student.getName(), "Student's name"));

        // Eger herhangi bir failure var ise yine de diger assertion'lar calistirilacaktir.
        assertAll("Student's name character check",
                () -> assertTrue(student.getName().startsWith("ca")),
                () -> assertTrue(student.getName().startsWith("ca"), () -> "Student's name " + "starts with 'ca'"),
                () -> assertFalse(() -> {
                    Student student2 = new Student("2", "cem", "berberoglu");
                    return student2.getName().startsWith("ca");
                }, () -> "Student's name " + "starts with 'ce'"));

        // dependent groups
        // Farkli executable icerisindekiler birbirine bagimli (dependent) degildir.
        assertAll("dependent assertions",
                () -> {
                    // Executable1
                    Student student2 = new Student("2", "cem", "berberoglu");
                    assertArrayEquals(new String[]{"can", "cem"}, Stream.of(student, student2).map(Student::getName).toArray());
                },
                () -> {
                    // Executable2
                    // Aynı executable icerisinde birden fazla assert varsa bunlar birbirine bagimlidir(dependent)
                    Student student2 = new Student("2", "cem", "berberoglu");
                    assertSame(student, student);
                    assertNotSame(student, student2);
                });

    }

}
