package io.can.unittestingdemo.test._02.junitBasics;

import io.can.unittestingdemo.project.models.LecturerCourseRecord;
import io.can.unittestingdemo.project.models.Student;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
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

    @Test
    @DisplayName("Got an exception when a null lecturer course record to student")
    void throwsExceptionWhenAddToNullCourseToStudent() {
        Student student = new Student("1", "can", "berberoglu");
        // assertThrows -> throw edilen exception'ı yakalar ve metoda verilen exception turu ile throw edilen exception turunu karsilastirir.
        assertThrows(IllegalArgumentException.class, () -> student.addCourse(null));
        assertThrows(IllegalArgumentException.class, () -> student.addCourse(null), "throws IllegalArgumentException");

        // assertThrows throw edilen exception'i return edebilir. Bu sayede farkli assertion yapabiliriz.
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> student.addCourse(null));
        assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Add course to a student less than 10ms")
    void addCourseToStudentWithATimeConstraint() {
        // Bu test belli bir zaman icerisinde calismasini istedigimiz kodlar icindir.
        // (Unit seviyesinde performans testi)

        // assertTimeOut -> Bir kodun verilen süre icerisinde calisirsa test basarili olur.
        // Calisma suresi verilen sureyi gecerse test failure olacaktir.

        // Bu assertion icerisinde bir kod parcasi olmadigi icin 10ms altinda calisacaktir.
        // with Executable
        assertTimeout(Duration.ofMillis(10L), () -> {

        });

        // ThrowingSupplier ile kodun return ettigi bir degeri yakalayabiliriz
        // with ThrowingSupplier
        String result = assertTimeout(Duration.ofMillis(10L), () -> {
            return "some string result";
        });
        assertEquals("some string result", result);

        Student student = new Student("1", "can", "berberoglu");
        LecturerCourseRecord course = new LecturerCourseRecord();
        // assertTimeout metodun tamamen bitmesini bekler. Bazen bitmesini istemeyebiliriz.
        assertTimeout(Duration.ofMillis(200L), () -> student.addCourse(course));

        // assertTimeoutPreemptively -> Surenin aşılıp aşılmadığına bakar. Eğer aşıldıysa metodu durdurur(interrupt)
        // ve Test failure olur
        assertTimeoutPreemptively(Duration.ofMillis(100L), () -> student.addCourse(course));
    }


    // Assumptions (varsayım) -> Bir varsayıma bağlı olarak testlerin çalışmasını sağlar
    // Örneğin bazı testlerin sadece dev ortamında çalışmasını isteyebiliriz. Jenkins gibi bir aracın
    // bu testi çalıştırmasını istemeyebiliriz. Bu varsayımda assumption'ları kullanabiliriz
    // Varsayımsal olarak çalışmasını istediğimiz testleri metot veya assertion bazında ayarlayabiliriz.

    @Test
    @DisplayName("Test student creation at only development machine")
    void shouldCreateStudentWithNameAndSurnameAtDevelopmentMachine() {


        // assumeTrue -> varsayımın true değerine göre tüm testi calistirilip calistirilmayacagini belirler.
        // assumeTrue metodunun varsayimi true degil ise testin devamı ignore edilecektir(calismayacaktir).

        // assumeFalse -> assumeTrue tersi

        // assumeTrue ve assumeFalse -> tum test metodunun ignore edilmesini saglar

        //Assumptions.assumeTrue(System.getProperty("ENV") != null,
        //        "Aborting test: System property ENV doesn't exist");

        //Assumptions.assumeTrue(System.getProperty("ENV").equals("dev"),
        // "Aborting test: Not an developer machine");

        Assumptions.assumeTrue("can".equals("can"), "This assumption is true. And test will run");

        final Student student = new Student("1", "can", "berberoglu");
        assertAll("Student Information",
                () -> assertEquals("can", student.getName()),
                () -> assertEquals("berberoglu", student.getSurname()),
                () -> assertEquals("1", student.getId()));


    }

    @Test
    @DisplayName("Test student creation at different environments")
    void shouldCreateStudentWithNameAndSurnameWithSpecificEnvironment() {

        // assumingThat -> assumeTrue ve assumeFalse 'un aksine tüm test methodunun degil,
        // bazi assertion'larin ignore edilmesini, bazilarinin ignore edilmemesini saglar

        Student student = new Student("1", "can", "berberoglu");

        String env = System.getProperty("ENV");

        // Eger assumption true degil ise executable ignore edilecektir. (ignore)
        Assumptions.assumingThat(env != null && env.equals("dev"), () -> {
            LecturerCourseRecord course = new LecturerCourseRecord();
            student.addCourse(course);
            assertEquals(1, student.getStudentCourseRecords().size());
        });

        Assumptions.assumingThat("can".equals("can"), () -> {
            LecturerCourseRecord course = new LecturerCourseRecord();
            student.addCourse(course);
            assertEquals(1, student.getStudentCourseRecords().size());
        });

        // Herhangi bir assumption'a bagli degil. Her türlü calistirilacaklardir.
        assertAll("Student Information",
                () -> assertEquals("can", student.getName()),
                () -> assertEquals("berberoglu", student.getSurname()),
                () -> assertEquals("1", student.getId()));

    }



}
