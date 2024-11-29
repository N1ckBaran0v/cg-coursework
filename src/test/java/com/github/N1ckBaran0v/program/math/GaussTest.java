package com.github.N1ckBaran0v.program.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaussTest {
    private static final double EPSILON = 1e-6;

    @Test
    void solve() {
        var matrix = new double[][]{
                {1, 2},
                {3, 4}
        };
        var result = new double[]{7, 17};
        var answer = new double[]{3, 2};
        Gauss.solve(matrix, result);
        assertArrayEquals(answer, result, EPSILON);
    }

    @Test
    void negative() {
        try {
            var matrix = new double[][]{
                    {1, 1},
                    {1, 1}
            };
            var result = new double[]{7, 17};
            Gauss.solve(matrix, result);
            fail();
        } catch (ArithmeticException ignored) {
        }
    }
}