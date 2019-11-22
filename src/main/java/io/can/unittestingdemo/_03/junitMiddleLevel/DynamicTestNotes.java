package io.can.unittestingdemo._03.junitMiddleLevel;

public class DynamicTestNotes {

    // Junit'de dinamik testler runtime'da dinamik olarak olusturuluyor.

    // Dinamik testler @TestFactory annotation'ı ile isaretlenmelidir. Bu metotlar test
    // metotlari degil, test metodu ureten factory metotlaridir.

    // Donus tipleri icerisinde DynamicNode objesi bulunan Stream, Iterable, Collection ve Iterator olmalidir.

    // DynamicNode class'ini extend eden DynamicTest ve DynamicContainer class'lari vardir.
    // DynamicTest -> İcerisinde bir executable bir instance barindiran gercekten calistirilan
    // test metodunu barindiran class'tir.
    // DynamicContainer -> Hiyerarşik bir dynamic test yapisi kurmayi saglayan, icerinde
    // baska DynamicTest veya DynamicContainer barindiran bir class'tir. (Nested gibi)

    // Dinamik testlerde @BeforeEach ve @AfterEach yazmak mumkun degil.
}
