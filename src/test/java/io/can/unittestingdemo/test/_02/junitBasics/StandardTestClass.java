package io.can.unittestingdemo.test._02.junitBasics;

import org.junit.jupiter.api.*;

import java.util.Random;

public class StandardTestClass {

    private static String oneInstancePerClass;
    private Integer oneInstancePerMethod;

    // @BeforeAll ve @AfterAll ile isaretlenen metotlar static ve void olmalıdır. default package veya public olabilir.

    // Asagidaki annotation'lara sahip metotların hepsi void olmalıdır. Default package veya public olabilirler.
    // Private olarak işaretlenirler ise Junit bu metotlari ignore edecektir.

    @BeforeAll // hicbir test methodu calistirilmadan önce bir defa çalışmasını sağlar
    static void initAll() {
        oneInstancePerClass = String.valueOf(new Random().nextInt());
        System.out.println("Init before all test method");
    }

    @AfterAll // bütün test methodları calistiktan sonra bir defa calisacak method oldugunu belirtir.
    static void tearDownAll() {
        oneInstancePerClass = null;
        System.out.println("Tear down after all test method");
    }

    @BeforeEach // her bir test çalışmadan önce çalışır. (@Test annotation sayısı kadar calisir.)
    void init() {
        oneInstancePerMethod = new Random().nextInt();
        System.out.println("Init before each test method");
    }

    @AfterEach // her bir test çalıştıktan sonra çalışır. (@Test annotation sayısı kadar calisir.)
    void tearDown() {
        oneInstancePerMethod = null;
        System.out.println("Tear down after each test method");
    }

    @Test // @Test -> Bir seyin test edildiği asıl test methodları olduğunu belirtir
    private void testSomething1() {
        System.out.println("Test: testSomething1: " + oneInstancePerMethod + " : " + oneInstancePerClass);
    }

    @Test
    @DisplayName("Custom test name") // test metoduna özel isim vermek icin kullanilir. Metot ismi yerine
    // @DisplayName icerisindeki isim gozukecektir.
    void testSomething2() {
        System.out.println("Test: testSomething2: " + oneInstancePerMethod + " : " + oneInstancePerClass);
    }

    @Test
    @Disabled("this test disabled and not execute for now") // calismasini istemedigimiz test metotlari icin kullanilir.
    // Yani bu test metodunu ignore et
    void testSomething3() {
        System.out.println("Test: testSomething3");
    }

    @Test
    void testSomethingToFail() {
        Assertions.fail("A failing test"); // Calisan test metodunun fail olarak isaretlenmesini saglar.
    }

}
