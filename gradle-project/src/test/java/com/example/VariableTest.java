package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {

    @Test
    @DisplayName("Define single variable")
    void testDefineSingleVariable() {
        String[][] vars = new String[100][2];
        int count = variable.define(vars, 0, "define x 10");
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Define multiple variables")
    void testDefineMultipleVariables() {
        String[][] vars = new String[100][2];
        int count = 0;
        count = variable.define(vars, count, "define x 10");
        count = variable.define(vars, count, "define y 20");
        count = variable.define(vars, count, "define z 30");
        assertEquals(3, count);
    }

    @Test
    @DisplayName("Redefine existing variable")
    void testRedefineVariable() {
        String[][] vars = new String[100][2];
        int count = 0;
        count = variable.define(vars, count, "define x 10");
        count = variable.define(vars, count, "define x 20");
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Redefine variable multiple times")
    void testRedefineVariableMultipleTimes() {
        String[][] vars = new String[100][2];
        int count = 0;
        count = variable.define(vars, count, "define x 10");
        count = variable.define(vars, count, "define x 20");
        count = variable.define(vars, count, "define x 30");
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Define and redefine with other variables")
    void testMixedDefineAndRedefine() {
        String[][] vars = new String[100][2];
        int count = 0;
        count = variable.define(vars, count, "define x 10");
        count = variable.define(vars, count, "define y 20");
        count = variable.define(vars, count, "define x 5");
        count = variable.define(vars, count, "define z 30");
        assertEquals(3, count);
    }

    @Test
    @DisplayName("Define variables with negative values")
    void testDefineNegativeValues() {
        String[][] vars = new String[100][2];
        int count = 0;
        count = variable.define(vars, count, "define x -10");
        count = variable.define(vars, count, "define y -20");
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Define variables with zero")
    void testDefineZero() {
        String[][] vars = new String[100][2];
        int count = variable.define(vars, 0, "define x 0");
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Define many variables")
    void testDefineManyVariables() {
        String[][] vars = new String[100][2];
        int count = 0;
        count = variable.define(vars, count, "define a 1");
        count = variable.define(vars, count, "define b 2");
        count = variable.define(vars, count, "define c 3");
        count = variable.define(vars, count, "define d 4");
        count = variable.define(vars, count, "define e 5");
        assertEquals(5, count);
    }
}
