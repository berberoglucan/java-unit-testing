package io.can.unittestingdemo.test._03.junitMiddleLevel.pratics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static io.can.unittestingdemo._03.junitMiddleLevel.pratics.Pratik1PrimeFactors.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicTest.stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Pratik1PrimeFactorsTest {

    private Map<Integer, List<Integer>> primeFactorExpectations = new LinkedHashMap<>();

    @BeforeAll
    void setExpectations() {
        primeFactorExpectations.put(1, Collections.emptyList());
        primeFactorExpectations.put(2, Collections.singletonList(2));
        primeFactorExpectations.put(3, Collections.singletonList(3));
        primeFactorExpectations.put(4, Arrays.asList(2, 2));
        primeFactorExpectations.put(5, Collections.singletonList(5));
        primeFactorExpectations.put(6, Arrays.asList(2, 3));
        primeFactorExpectations.put(7, Collections.singletonList(7));
        primeFactorExpectations.put(8, Arrays.asList(2, 2, 2));
        primeFactorExpectations.put(9, Arrays.asList(3, 3));
        primeFactorExpectations.put(10, Arrays.asList(2, 5));
    }



    @Test
    @DisplayName("Generate prime factors with standard test")
    void generateWithStandardTest() {
        assertEquals(Collections.emptyList(), generate(1));
        assertEquals(Collections.singletonList(2), generate(2));
        assertEquals(Collections.singletonList(3), generate(3));
        assertEquals(Arrays.asList(2, 2), generate(4));
        assertEquals(Collections.singletonList(5), generate(5));
        assertEquals(Arrays.asList(2, 3), generate(6));
        assertEquals(Collections.singletonList(7), generate(7));
        assertEquals(Arrays.asList(2, 2, 2), generate(8));
        assertEquals(Arrays.asList(3, 3), generate(9));
        assertEquals(Arrays.asList(2, 5), generate(10));
    }

    // repeated test
    @RepeatedTest(value = 10)
    @DisplayName("Generate prime factors with repeated test")
    void generateWithRepeatedTest(RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        assertEquals(primeFactorExpectations.get(currentRepetition), generate(currentRepetition));
    }

    // parameterized test
    @ParameterizedTest
    @DisplayName("Generate prime factors with parameterized test")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void generateWithParameterizedTest(int number) {
        assertEquals(primeFactorExpectations.get(number), generate(number));
    }

    // dynamic test
    @TestFactory
    @DisplayName("Generate prime factors with dynamic test")
    Stream<DynamicTest> generateWithDynamicTest() {

        Iterator<Integer> source = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator();
        Function<Integer, String> displayNames = number -> "dynamic test " + number;
        ThrowingConsumer<Integer> testExecutor = number -> {
            assertEquals(primeFactorExpectations.get(number), generate(number));
        };

       return  stream(source, displayNames, testExecutor);
    }

    @TestFactory
    @DisplayName("Generate prime factors with dynamic test 2")
    Stream<DynamicNode> generateWithDynamicTest2() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map(number -> dynamicTest("dynamic test " + number,
                        () -> assertEquals(primeFactorExpectations.get(number), generate(number)))
                );
    }
}
