package io.can.unittestingdemo.test._02.junitBasics;

import io.can.unittestingdemo._02.junitBasics.Pratik1Fibonacci;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Pratik1FibonacciTest {

    @Test
    @DisplayName("Find fibonacci numbers for specific order")
    void findFibonacciNumbers() {
        // 1 1 2 3 5 8
        Pratik1Fibonacci fibonacci = new Pratik1Fibonacci();
        assertThrows(IllegalArgumentException.class,
                () -> fibonacci.find(0));

        assertAll("Fibonacci Numbers",
                () -> assertEquals(1, fibonacci.find(1)),
                () -> assertEquals(1, fibonacci.find(2)),
                () -> assertEquals(2, fibonacci.find(3)),
                () -> assertEquals(3, fibonacci.find(4)),
                () -> assertEquals(5, fibonacci.find(5)),
                () -> assertEquals(8, fibonacci.find(6)));
    }
}
