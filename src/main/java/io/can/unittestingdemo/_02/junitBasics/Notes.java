package io.can.unittestingdemo._02.junitBasics;

public class Notes {

    // 1) Assertion nedir?

    // -- Bir seyi test edebilmek için calisan production kodlarının ürettiği sonucu kontrol etmeliyiz.
    // Bu kontrol icin Assertion mekanizmasını kullanabiliriz.(Junit veya 3rd party assertion kütüphanesi)

    // -- Assertion -> Oluşmasını beklediğimiz durumlar veya çıktılar ile gerçekte olanın
    // karşılaştırılması ve bu ikisi arasında beklediğimiz uyum yok ise hata üretilmesidir.

    // -- Assertion yok ise testten söz edilemez.

    // -- Junit'deki assertion mekanizması bir dizi static metotdan olusmaktadır. Bu metotlara
    // Assertions class'ından ulasabilmekteyiz. (assertEquals(expected, actual))

    // -- Junit assertion metotları -> iki nesnenin veya primitive degiskenin esitligini,
    // bir degerin true ya da false olmasını, hata fırlatılıp fırlatılmadığını (exception) veya
    // production kodunun istenen zamanda çalışıp çalışmadığını (timeout) test eden metotlardan olusmaktadır.

    // -- Junit dışında kullanıbilen 3rd party assertion kütüphaneleri de bulunmaktadır.
    // Bunlar AssertJ, Hamcrest, Truth vb.
}
