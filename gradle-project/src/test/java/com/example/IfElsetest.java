package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class IfElsetest {
    @ParameterizedTest
    @CsvSource({
        "(if (5 > 3) 10 20), 10",
        "(if (3 > 5) 10 20), 20",
        "(if (10 > 10) 100 200), 200",
        "(if (15 > 5) 50 75), 50"
    })
    @DisplayName("Greater than")
    void testGreaterThan(String expr, int expected) {
        int result = IfElse.evaluate(expr);
        assertEquals(expected, result);
    }
    @ParameterizedTest
    @CsvSource({
        "(if (3 < 5) 10 20), 10",
        "(if (5 < 3) 10 20), 20",
        "(if (10 < 10) 100 200), 200",
        "(if (2 < 8) 30 40), 30"
    })
    @DisplayName("Less than")
    void testLessThan(String expr, int expected) {
        int result = IfElse.evaluate(expr);
        assertEquals(expected, result);
    }
    @ParameterizedTest
    @CsvSource({
        "(if (5 = 5) 10 20), 10",
        "(if (5 = 3) 10 20), 20",
        "(if (0 = 0) 100 200), 100",
        "(if (10 = 5) 50 75), 75"
    })
    @DisplayName("Equality")
    void testEquality(String expr, int expected) {
        int result = IfElse.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(if (5 >= 3) 10 20), 10",
        "(if (5 >= 5) 10 20), 10",
        "(if (3 >= 5) 10 20), 20",
        "(if (10 >= 10) 100 200), 100"
    })
    @DisplayName("Greater than or equal")
    void testGreaterThanOrEqual(String expr, int expected) {
        int result = IfElse.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(if (3 <= 5) 10 20), 10",
        "(if (5 <= 5) 10 20), 10",
        "(if (5 <= 3) 10 20), 20",
        "(if (10 <= 10) 100 200), 100"
    })
    @DisplayName("Less than or equal")
    void testLessThanOrEqual(String expr, int expected) {
        int result = IfElse.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(if (0 > 5) 10 20), 20",
        "(if (-5 < 0) 10 20), 10",
        "(if (-3 = -3) 100 200), 100",
        "(if (-10 >= -5) 50 75), 75",
        "(if (-5 <= -3) 30 40), 30"
    })
    @DisplayName("Negative numbers")
    void testNegativeNumbers(String expr, int expected) {
        int result = IfElse.evaluate(expr);
        assertEquals(expected, result);
    }
}
