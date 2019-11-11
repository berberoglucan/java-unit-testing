package io.can.unittestingdemo._01.whatIsUnitTest;

public class Notes1 {

    // 1) OOP'de unit test nedir?

     /* -- Class -> Objelerin doğduğu yerdir. Buna bir objenin instantiated edilmesi de denir.
     Yani class'tan somut bir örnek (varlık-> instance) oluşturulmasıdır.
     Bir class kendisinden üretilecek objelerin nasıl görünmesi ve nasıl davranılması
     gerektiğini bilir. Dolayısıyla bir class'ın temel sorumluluğu belirli bir görünüme ve davranışa
     sahip objelerin oluşturulmasına olanak vermektir. */

     /* -- Object -> Gerçek hayatta varolan bir şeyin bizim sistemimiz içindeki temsilcisidir.
     * İçerisinde data ve davranış barındıran, mümkünse gerçek hayattaki bir şeyin sistemimiz içindeki
     * temsilcisidir.
     */

     /* -- Unit -> Bir objenin herhangi bir davranışıdır. Örneğin Sipariş objesinin ödenmiş siparişlerinin
     * iptali ve ödenmemiş siparişlerin iptali birbirinden farklı, sipariş objesine ait birer davranışıdır (unit).
     * Her unit objede ayrı metoda karşılık gelmeyebilir. Ödenmiş ve ödenmemiş siparişlerin iptali icin tek
     * bir metot kullanılabilir.Fakat bu işlemleri birbirinden ayrı davranışlardır. Bunlar için ayrı unit test
     * yazılabilir.
     * Unit ve objenin metotları arasında her zaman birebir ilişki vardır diyemeyiz.
     */

     /* -- Unit Test -> Birbirinden farklı davranışların (unit'lerin) birbirinden bağımsız olarak test edilmesidir.
     *  Ödenmiş ve ödenmemiş siparişlerin iptali için çalışan tek metodun birbirinden ayrı olarak test edilmesi gerekir.
     *  Bu iki testin arasında herhangi bir bağımlılık bulunmamaktadır ve her bir test tek başına çalışabişir olmalıdır.
     *  Unit test'ler geliştirme aşamasının bir parçasıdırlar ve developer'lar tarafından yazılırlar.
     */

    // 2) Neler unit test olamaz? (Tartışılabilir)
    // -- Veritabanı ile konuşuyorsa
    // -- Network üzerinden bir iletişim kuruyorsa
    // -- Dosya sistemi ile bir şeyler yapılıyorsa
    // -- Herhangi bir unit test ile aynı anda çalıştığında doğru çalışmıyorsa
    // -- Testi çalıştırmak için herhangi bir konfigürasyon dosyasında değişiklik yapmak gerekiyorsa

    // Bu özelliklere sahip testler kötü olmayabilir, (hatta bu özelliklere ait testlerin de yazılması gerekir)
    // ancak bu özellikler unit test kümesinden ayrılmalı ki, sistem de bir değişiklik olduğunda
    // hızlı bir şekilde çalıştırıp geri bildirim alınabilecek bir unit test kümesi olabilsin.




}
