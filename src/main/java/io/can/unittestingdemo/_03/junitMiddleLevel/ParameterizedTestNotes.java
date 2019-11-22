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

    // 5) Type Conversion -> Parametrik testlerde, test metotlarının aldığı parametreler ile
    // kaynakların(source'ların Orn. @ValueSource) sagladigi parametreler arasinda
    // otomatik type conversion yapilabilir. İstersek custom type conversion da yapabiliriz.
    // Ornegin @ValueSource icerisinde belli formatta yazilmis String ifadeyi, test metoduna
    // parametre olarak gecilen bir LocalDateTime objesine convert edebiliriz.

    // TODO: @CsvSourceFile ile dosyadanda degerler okunabilir. Gerekirse ornek yap.

    // TODO: @ArgumentSource sonra bakilabilir.

}
