package io.can.unittestingdemo.test._01.whatIsUnitTest;

import io.can.unittestingdemo._01.whatIsUnitTest.FizzBuzz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    // 3'e bölünüyorsa String olarak "Fizz"
    @Test
    void returnFizzWhenTheNumberDividedByThree() {
        assertEquals("Fizz", fizzBuzz.stringFor(3));
    }

    // 5'e bölünüyorsa String olarak "Buzz"
    @Test
    void returnBuzzWhenTheNumberDividedByFive() {
        assertEquals("Buzz", fizzBuzz.stringFor(5));
    }

    // Hem 3'e hem de 5'e bölünüyorsa String olarak "FizzBuzz"
    @Test
    void returnFizzBuzzWhenTheNumberDividedBothOfThreeAndFive() {
        assertEquals("FizzBuzz", fizzBuzz.stringFor(15));
    }

    // Hem 3'e hem de 5'e bölünmüyorsa sayının kendisini
    @Test
    void returnTheNumberItselfWhenTheNumberIsNotDividedBothOfThreeAndFive() {
        assertEquals("19", fizzBuzz.stringFor(19));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenTheNumberIsLessThanOneOrGreaterThanHundred() {
        assertThrows(IllegalArgumentException.class, () -> fizzBuzz.stringFor(-1));
        assertThrows(IllegalArgumentException.class, () -> fizzBuzz.stringFor(102));
    }

}
