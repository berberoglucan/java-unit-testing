package io.can.unittestingdemo._03.junitMiddleLevel;

public class JunitParameterResolverNotes {

    // JUnit bazi ParameterResolver interface'ini implements eden
    // class'lari test methodlarina ve constructor'a parametre olarak bind(inject) edebilmektedir.(Spring'teki gibi, DI)

    // Built-in -> TestInfo, TestReporter
    // TestInfo -> test methodu ile ilgili bilgeleri saglar. (display name, tag vs.)
    // TestReporter -> test metodu calisirken ekstra parametreleri key-value olarak yayinlamamizi saglar.

    // Custom olarak parameter resolver'lar yazabiliriz.

}
