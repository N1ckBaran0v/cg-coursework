package com.github.N1ckBaran0v.program.math;

import org.jetbrains.annotations.NotNull;

public final class Gauss {
    private static final double EPSILON = 1e-6;

    private Gauss() {
    }

    public static void solve(double @NotNull [] @NotNull [] matrix, double @NotNull [] answer) {
        var size = answer.length;
        if (size != matrix.length) {
            throw new IllegalArgumentException("Matrix does not have the same number of elements");
        }
        for (var line : matrix) {
            if (line.length != size) {
                throw new IllegalArgumentException("Matrix does not have the same number of elements");
            }
        }
        for (var i = 0; i < matrix.length; ++i) {
            var max = Math.abs(matrix[i][i]);
            var index = i;
            for (var j = i + 1; j < matrix.length; ++j) {
                var curr = Math.abs(matrix[j][i]);
                if (curr > max) {
                    index = j;
                    max = curr;
                }
            }
            if (Math.abs(max) < EPSILON) {
                throw new ArithmeticException();
            }
            var tmp1 = matrix[index];
            matrix[index] = matrix[i];
            matrix[i] = tmp1;
            var tmp2 = answer[index];
            answer[index] = answer[i];
            answer[i] = tmp2;
            for (var j = i; j < matrix.length; ++j) {
                double tmp = matrix[j][i];
                if (Math.abs(tmp) < EPSILON) {
                    continue;
                }
                for (var k = i; k < matrix.length; ++k) {
                    matrix[j][k] /= tmp;
                }
                answer[j] /= tmp;
                if (j == i) {
                    continue;
                }
                for (var k = i; k < matrix.length; ++k) {
                    matrix[j][k] -= matrix[i][k];
                }
                answer[j] -= answer[i];
            }

        }
        for (var i = matrix.length - 1; i >= 0; --i) {
            for (var j = 0; j < i; ++j) {
                answer[j] -= matrix[j][i] * answer[i];
            }
        }
    }
}
