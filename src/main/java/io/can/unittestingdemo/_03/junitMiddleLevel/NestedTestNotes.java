package io.can.unittestingdemo._03.junitMiddleLevel;

public class NestedTestNotes {

    // Birbiri ile iliski test senaryolarını daha okunabilir ve organize edebilmek icin
    // ic ice (nested) testler kullanilabilir.

    // Her nested test birer inner class'tir. @Nested annotation'ı ile isaretlenmelidir.

    // Nested test'ler de PER_METHOD hayat dongusu icin BeforeAll ve AfterAll kullanilamaz.
    // PER_CLASS icin bir kisit yok.

}
