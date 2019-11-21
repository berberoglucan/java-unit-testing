package io.can.unittestingdemo._03.junitMiddleLevel;

public class ParameterizedTestNotes {

    // ParameterizedTest -> JUnit bir test metodunu farklı farklı parametreler ile
    // birden fazla çalıştırmaya olanak saglar. Bu testlere parametrik testler denir.

    // junit-jupiter-params dependency'si eklenmelidir.

    // ParameterizedTest yapmak icin iki sey yapmak gerekir.
    // 1) @ParameterizedTest annotation'ı ile isaretlemek
    // 2) Testlere saglanacak parametrelere kaynak belirtmek. Belirtilen parametre kaynagi
    // ne kadar degisik parametre sagliyor ise test o kadar sayida calisacaktir.

    // Parametre kaynaklari
    // @ValueSource, @EnumSource, @MethodSource, @CsvSource, @CsvFileSource, @ArgumentSource

    // 1) @ValueSource -> int, long vb primitive tiplerden, String ve herhangi bir class tipinden
    // olan bir dizinin elemanlarını teste parametre olarak vermektedir.

    // 2) @EnumSource -> Bir enum'un tüm degerlerini veya bazi degerlerini teste parametere
    // olarak verir.

    // 3) @MethodSource -> Test metotlarina parametreleri saglayacak bir factory metodunu alir ve test metoduna factory'nin
    // olusturdugu degerleri parametre olarak verir.
    // Factory metot ismi annotation'a verilir.
    // Bu factory metot Stream, Iterator, Iterable ve ya Array tipinde obje donmelidir.
    // Test metodu lifecycle per class degil ise static olmalidir.

    // 4) @CsvSource -> Delimeter(,) ile ayrilmis her bir String dizilerini test metoduna parametre
    // olarak gecemketedir.
    // CSV => Comma seperated values

    // TODO: @CsvSourceFile ile dosyadanda degerler okunabilir. Gerekirse ornek yap.

    // TODO: @ArgumentSource sonra bakilabilir.

    // TODO: junit bazi tipleri otomatik olarak convert ederek parametre icine verebilir.
    //  Bunun icin daha sonra type Conversion incelenebilir (Önemli)

}
