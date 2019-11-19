package io.can.unittestingdemo._03.junitMiddleLevel;

public class JUnitLifecycleNotes {

    // 1) Default lifecycle -> @TestInstance(Lifecycle.PER_METHOD) -> Test metotlarinin herbiri için test class'indan
    // instance olusturularak calistirilmasi

    // Bu lifecycle'da bir test class'inin her test metodu calismadan once test class'indan bir obje olusturuyor.
    // Yani ne kadar test metodu var ise o kadar test class'inin instance'ı var demektir.
    // Eger test class'inda bir state(field) var ise bu state test metotlarinda paylasilamamaktadir.
    // Yani state sifirlanmaktadir.

    // 2) @TestInstance(Lifecycle.PER_CLASS) -> Test metotlarinin tek bir test class'ından instance olusturularak calistirilmasi
    // Yani ne kadar test metodu var ise sadece bir adet test class'inin instance'ı var demektir.
    // Eger test class'inda bir state(field) var ise bu state'in degerine test metotlarindan ulasilabilir(paylasilabilir)

    // PER_CLASS icin state'in degerini test metotlari gecisinde
    // sifirlanmak istenirse @BeforeEach ve @AfterEach kullanilabilir.
    // @BeforeAll ve @AfterAll static olmayabilir.
}
