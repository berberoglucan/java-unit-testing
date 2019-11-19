package io.can.unittestingdemo.test._03.junitMiddleLevel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.time.LocalDate;

// interface icerisinde default method ile bazi islemleri tanimlayabiliriz.
public interface TestLifecycleReporter {

    @BeforeEach
    default void setUp(TestInfo testInfo, TestReporter reporter) {
        if (testInfo.getDisplayName() != null) {
            System.out.println(testInfo.getDisplayName() + " is started");
        } else {
            System.out.println(testInfo.getTestMethod().get().getName() + " is started");
        }
        reporter.publishEntry("start time: " , LocalDate.now().toString());
    }

    @AfterEach
    default void tearDown(TestInfo testInfo, TestReporter reporter) {
        if (testInfo.getDisplayName() != null) {
            System.out.println(testInfo.getDisplayName() + " is finished");
        } else {
            System.out.println(testInfo.getTestMethod().get().getName() + " is finished");
        }
        reporter.publishEntry("Finish time: " , LocalDate.now().toString());
    }
}
