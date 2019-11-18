package io.can.unittestingdemo._02.junitBasics;

public class Pratik1Fibonacci {

    public int find(int order) {

        if (order <= 0) {
            throw new IllegalArgumentException("order not be less then 0");
        }

        if (order == 1 || order == 2) {
            return 1;
        }

        return find(order - 2) + find(order - 1);
    }
}
