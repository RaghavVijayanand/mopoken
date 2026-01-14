import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class Prefixtest {
    private prefix interpreter;

    @BeforeEach
    void setUp() {
        interpreter = new prefix();
    }

    @ParameterizedTest
    @CsvSource({
        "(+ 1 2), 3",
        "(+ 10 20), 30",
        "(+ 0 0), 0",
        "(+ -5 5), 0",
        "(+ 100 -50), 50"
    })
    @DisplayName("Addition tests")
    void testAddition(String expr, int expected) {
        Object result = interpreter.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(- 5 3), 2",
        "(- 10 20), -10",
        "(- 0 0), 0",
        "(- -5 5), -10",
        "(- 100 50), 50"
    })
    @DisplayName("Subtraction tests")
    void testSubtraction(String expr, int expected) {
        Object result = interpreter.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(* 2 3), 6",
        "(* 10 5), 50",
        "(* 0 10), 0",
        "(* -5 3), -15",
        "(* -2 -4), 8"
    })
    @DisplayName("Multiplication tests")
    void testMultiplication(String expr, int expected) {
        Object result = interpreter.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(/ 6 3), 2",
        "(/ 20 5), 4",
        "(/ 100 10), 10",
        "(/ -10 2), -5",
        "(/ -20 -4), 5"
    })
    @DisplayName("Division tests")
    void testDivision(String expr, int expected) {
        Object result = interpreter.evaluate(expr);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "(+ (* 2 3) 4), 10",
        "(- (+ 10 5) 3), 12",
        "(* (+ 2 3) (- 10 5)), 25",
        "(/ (+ 20 10) (- 10 5)), 6"
    })
    @DisplayName("Nested operations tests")
    void testNestedOperations(String expr, int expected) {
        Object result = interpreter.evaluate(expr);
        assertEquals(expected, result);
    }
}