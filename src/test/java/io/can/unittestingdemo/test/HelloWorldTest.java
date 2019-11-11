package io.can.unittestingdemo.test;

import io.can.unittestingdemo.HelloWorld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HelloWorldTest {

    @Test
    public void helloWorldTest() {
        HelloWorld helloWorld = new HelloWorld();
        Assertions.assertEquals("Hello World!", helloWorld.sayHello(), "Not equals");
    }

}
