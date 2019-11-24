package io.can.unittestingdemo.test._05.assertj;

import io.can.unittestingdemo.project.models.*;
import org.assertj.core.api.Condition;
import org.assertj.core.api.iterable.ThrowingExtractor;
import org.junit.jupiter.api.*;

import java.awt.image.CropImageFilter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithAssertJAssertions {

    @Test
    @DisplayName("AssertJ String Assertions")
    void createStudentWithStringAssertions() {
        Student student = new Student("1", "can", "berberoglu");
        assertThat(student.getName())
                // as -> assertion fail olursa yazdirilacak mesaj. Once yazilmali
                .as("Student's name %s ", student.getName())
                // doesNotContainAnyWhitespaces -> String sadece bosluklardan olusmamali (blank)
                // null, "", icin gecerli degildir. String'de hic bosluk olmamali
                .doesNotContainAnyWhitespaces()
                // isNotEmpty -> "" ve null olmamali
                .isNotEmpty()
                // isNotBlank -> "", null ve " " olmamali
                // isNotBlank = isNotEmpty + doesNotContainAnyWhitespaces
                .isNotBlank()
                // isEqualTo -> esitlik icin. Normal equals metodu
                .isEqualTo("can")
                // isEqualToIgnoringCase -> buyuk kucuk harf duyarliligi olmadan equals
                .isEqualToIgnoringCase("CAN")
                // isIn -> verilenlerden en az birine esit mi? kontrolu yapar.
                .isIn("can", "cem", "suleyman")
                // isNotInt -> isIn metodunun tersi, icerisinde yok anlaminda
                .isNotIn("Ali", "Veli")
                // startsWith -> Buyuk kucuk harf duyarli, string verilen prefix ile basliyor mu?
                .startsWith("ca")
                // doesNotStartWith -> startWith'in tersi
                .doesNotStartWith("Ca")
                // endsWith -> Buyuk kucuk harf duyarli, string verilen deger ile bitiyor mu?
                .endsWith("an")
                // doesNotEndWith -> endsWith tersi
                .doesNotEndWith("N")
                // contains -> icerisine verilen tum parametreleri iceriyor mu? bir tanesini icermiyorsa
                // assertion fail olacaktir.
                .contains("can", "a", "an", "c")
                .contains(Arrays.asList("can", "a", "an", "c"))
                .contains("an")
                // hasSize -> String ifadenin lengt degerine bakar.
                .hasSize(3)
                // matches -> verilen regex ifadeye uyuyor mu diye kontrol edilir.
                .matches("can")
                .matches("^c\\w{1}n$");

    }

    @Test
    @DisplayName("AssertJ Collections Assertions")
    void addCourseToStudent() {
        final Student ahmet = new Student("id1", "Ahmet", "Yilmaz", LocalDate.of(1990, 1, 1));
        final Student mehmet = new Student("id2", "Mehmet", "Kural", LocalDate.of(1992, 1, 1));
        final Student canan = new Student("id3", "Canan", "Sahin", LocalDate.of(1995, 1, 1));
        final Student elif = new Student("id4", "Elif", "Oz", LocalDate.of(1991, 1, 1));
        final Student hasan = new Student("id5", "Hasan", "Kartal", LocalDate.of(1990, 1, 1));
        final Student mucahit = new Student("id6", "Mucahit", "Kurt", LocalDate.of(1980, 1, 1));

        final List<Student> students = Arrays.asList(ahmet, mehmet, canan, elif, hasan);

        assertThat(students).as("Student's List")
                // isNotNull -> collection null olmamali
                .isNotNull()
                // isNotEmpty -> collection size 0'dan buyuk olmali
                .isNotEmpty()
                // hasSize -> collection size 5'mi
                .hasSize(5)
                // contains -> verilen objeler liste var mi? sira onemli degil. Olmayan var ise
                // fail olacaktir.
                .contains(canan, ahmet)
                // containsExactly -> verilen degerlerin collection'da olmasi ve siralamasi dogru olmalidir.
                .containsExactly(ahmet, mehmet, canan, elif, hasan)
                // containsOnly -> verilen degerlerin collection'da olmasi gerekli
                // Ama siralamasi dogru olmasa da olur.
                .containsOnly(ahmet, canan, mehmet, hasan, elif)
                //containsExactlyInAnyOrder -> containsOnly gibi calisir.
                .containsExactlyInAnyOrder(ahmet, canan, mehmet, hasan, elif);

        // collection filtrelemesi with predicate -> 25 yastan buyuk olanlar
        assertThat(students)
                .filteredOn(student -> student.getDob().until(LocalDate.now(), ChronoUnit.YEARS) >= 25)
                .hasSize(4)
                .containsOnly(ahmet, mehmet, elif, hasan);

        // collection filtrelemesi with Condition
        assertThat(students)
                .filteredOn(new Condition<>(
                        student -> student.getDob().until(LocalDate.now(), ChronoUnit.YEARS) >= 25,
                        "25 yas ustu"))
                .hasSize(4)
                .containsOnly(ahmet, mehmet, elif, hasan);

        // collection filtrelemesi with FilterOperator
        // class'in dob field'ina gore degeri bu olanlari filtreliyoruz
        assertThat(students)
                .filteredOn("dob", in(LocalDate.of(1990, 1, 1)))
                .hasSize(2)
                .containsOnly(ahmet, hasan);

        //collection filtrelemesi with expected value
        assertThat(students)
                .filteredOn("name", "Ahmet")
                .hasSize(1)
                .containsOnly(ahmet);

        // extracting -> Stream map metodu gibi collection'i manipule eder.
        // asagidaki -> stream().map(Function).filter(Predicate) mantiginda
        assertThat(students)
                .extracting(Student::getName)
                .filteredOn(name -> name.contains("e"))
                .hasSize(2)
                .containsOnly("Ahmet", "Mehmet");

        // tuple kullanimi -> ad ve soyada gore contains match yapabiliyor.
        // en cok kullanilabileceklerden
        assertThat(students)
                .filteredOn(student-> student.getName().contains("e"))
                .extracting(Student::getName, Student::getSurname)
                .containsOnly(
                        tuple("Ahmet", "Yilmaz"),
                        tuple("Mehmet", "Kural")
                );

        final LecturerCourseRecord lecturerCourseRecord101 = new LecturerCourseRecord(new Course("101"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord103 = new LecturerCourseRecord(new Course("103"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord105 = new LecturerCourseRecord(new Course("105"), new Semester());

        ahmet.addCourse(lecturerCourseRecord101);
        ahmet.addCourse(lecturerCourseRecord103);

        canan.addCourse(lecturerCourseRecord101);
        canan.addCourse(lecturerCourseRecord103);
        canan.addCourse(lecturerCourseRecord105);

        // flatExctracting -> Stream'lerde flatMap gibi farkli olan collection'lari
        // birlestirir ve tek bir collection yapar
        assertThat(students)
                .filteredOn(student -> student.getName().equals("Ahmet") ||
                        student.getName().equals("Canan"))
                // burada 5 elemanli bir yeni studentCourseRecord collection'i olustu
                .flatExtracting(Student::getStudentCourseRecords)
                .hasSize(5)
                .filteredOn(course -> course.getLecturerCourseRecord().getCourse().getCode().equals("101"))
                .hasSize(2);
    }

    @Test
    @DisplayName("AssertJ Custom Object Assertions")
    void createStudent() {
        Student cem = new Student("1", "cem", "berberoglu");
        Student can = new Student("2", "can", "berberoglu");

        assertThat(can).as("Checkt student can info")
                .isNotNull()
                //hasSameClassAs -> verilen tiplerin ayni class mi degil mi diye bakilir.
                .hasSameClassAs(cem)
                // isExactlyInstanceOf -> obje verilen class'in bir objesi mi?
                // Kalitim bu duruma dahil degildir. Super class verilirse fail olur.
                .isExactlyInstanceOf(Student.class)
                // isInstanceOf -> isExactlyInstanceOf aksine super class kabul eder.
                .isInstanceOf(Object.class)
                .isNotEqualTo(cem)
                // isEqualToComparingOnlyGivenFields -> objenin field'lerine gore karsilastirma yapar.
                // surname'ler esit oldugu icin bu assertion gececektir.
                .isEqualToComparingOnlyGivenFields(cem, "surname")
                // isEqualToIgnoringGivenFields -> equals kontrolü yaparken verilen field'leri ignore eder.
                // Yukaridaki isEqualToComparingOnlyGivenFields kullanimi ile ayni oldu.
                .isEqualToIgnoringGivenFields(cem, "id", "name", "dob")
                // isEqualToComparingFieldByField -> objelerin tum field'leri esit mi diye kontrol edecektir.
                //.isEqualToComparingFieldByField(cem)
                // matches -> obje matches icerisine verilen predicate'e uyuyor mu diye kontrol eder.
                .matches(student -> student.getName().startsWith("can"))
                // hasFieldOrProperty -> objede boyle bir property var mi?
                .hasFieldOrProperty("name")
                .hasFieldOrPropertyWithValue("name", "can")
                // hasNoNullFieldsOrProperties -> objenin propertyler'i null degil mi? kontrolu yapar.
                // bir tanesi bile null ise assertion fail olur.
                // .hasNoNullFieldsOrProperties()
                // extracting -> belli bazi field'leri extract edip karsilastirabiliyoruz.
                .extracting(Student::getName, Student::getSurname).containsOnly("can", "berberoglu");

        // custom assertion with custom object

        CustomStudentAssert.assertThat(can).as("Custom assertion")
                .hasName("can");

    }

    @Test
    @DisplayName("AssertJ Exception Assertions")
    void addCourseToStudentWithExceptionAssertions() {
        Student can = new Student("1", "can", "berberoglu");
        // assertThatThrownBy -> oncelikle exception firlatacak metot yazilir.
        // daha sonra exception spesifik metotlar kullanilir.
        assertThatThrownBy(() -> can.addCourse(null))
            .isInstanceOf(IllegalArgumentException.class)
                // hasMessageContaining -> exception message'i bunu iceriyor mu?
                .hasMessageContaining("Can't add course")
                // hasStackTraceContaining -> stack trace boyle bir sey iceriyor mu?
                .hasStackTraceContaining("Student")
        ;

        // assertThatExceptionOfType -> assertThatThrownBy ile ayni
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> can.addCourse(null))
                .withMessageContaining("Can't add course")
                .withNoCause(); // cause yok

        // bazı çok kullanılan exception'lar icin hazir assertion'lar bulunmaktadir.
        // Ornegin IllegalArgumentException, NullPointerException vb.

        // Illegal argument exceotion
        assertThatIllegalArgumentException()
                .isThrownBy(() -> can.addCourse(null))
                .withMessageContaining("Can't add course")
                .withNoCause();

        // assertThatCode -> yukaridakiler ile ayni islemi yapabilir
        // Ekstra olarak bir metodun hata firlatmadiginin test edilmesini saglar

        assertThatCode(() -> can.addCourse(new LecturerCourseRecord(new Course("101"), new Semester())))
                .doesNotThrowAnyException();

        // istenirse firlatilan exception'in instance'i alinabilir.
        Throwable throwable = catchThrowable(() -> can.addCourse(null));
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Can't add course");

        // TODO: Cause yüzünden firlatilan exceptionlara da bak lazım olursa

    }
}
