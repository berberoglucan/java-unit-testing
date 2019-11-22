package io.can.unittestingdemo._03.junitMiddleLevel.pratics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pratik1PrimeFactors {

    // carpanlarina ayirmak
    public static List<Integer> generate(int i) {
        if (i <= 1) {
           return Collections.emptyList();
        }
        List<Integer> primeFactors = new ArrayList<>();
        for (int candidate = 2; i > 1; candidate++) {
            for (; i % candidate == 0; i /= candidate) {
                primeFactors.add(candidate);
            }
        }
        return primeFactors;
    }
}
