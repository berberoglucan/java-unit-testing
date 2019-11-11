package io.can.unittestingdemo._01.whatIsUnitTest;

public class Notes2 {

    // 3) Unit Test'in Yapısı
    // Unit test belirli bir davranışın testine odaklandığı için objenin de bu davranışı gösterebilmesi
    // belirli bir durumda(state) olması gerekir

    // -- Given (Ön Koşul) -> Objenin istenilen duruma(state) getirilmesi
    // Orn: Odenmemiş durumda bir sipariş oluştur

    // -- When (Aksiyon) -> Objeden istediğimiz aksiyonu almasını istemek
    // Orn: Siparişin iptal metodunu çağır

    // -- Then (Sonuç) -> Objenin gelmesini beklediğimiz yeni durumun kontrolü
    // Orn: Sipariş iptal durumuna gelmiş mi ? Kontrol et

    // -- Clean -> Objenin gerekiyorsa test öncesi duruma getirilmesi
    // Orn: Ortak bir sipariş kullanılıyorsa sipariş ilk duruma getirilir.

}
