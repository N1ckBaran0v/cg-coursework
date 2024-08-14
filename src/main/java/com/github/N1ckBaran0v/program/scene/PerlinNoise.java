package com.github.N1ckBaran0v.program.scene;

import java.util.Objects;
import java.util.Random;

class PerlinNoise {
    private final int octaves = 4;
    private final double sideSize;
    private static final int TABLE_SIZE = 512;
    private final int[] table = new int[TABLE_SIZE];

    public PerlinNoise(long seed, double sideSize) {
        this.sideSize = sideSize;
        var gen = new Random(seed);
        for (var i = 0; i < TABLE_SIZE; ++i) {
            table[i] = gen.nextInt(256);
        }
    }

    public double get(long x, long y) {
        x = x > 0 ? x : -x;
        y = y > 0 ? y : -y;
        var dx = x / sideSize + 0.314;
        var dy = y / sideSize + 0.159;
        var amplitude = 1.0;
        var maxval = 0.0;
        var result = 0.0;
        for (var i = 0; i < octaves; ++i) {
            maxval += amplitude;
            result += noise(dx, dy) * amplitude;
            amplitude *= 0.5;
            dx *= 2;
            dy *= 2;
        }
        result /= 2 * maxval;
        result += 0.5;
        return result;
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    private double gradient(int hash, double x, double y) {
        return ((hash & 1) == 0 ? x : -x) + ((hash & 2) == 0 ? y : -y);
    }

    private int hashFunc(int x, int y) {
        return table[Objects.hash(x, y) % TABLE_SIZE];
    }

    private double noise(double x, double y) {
        var xi = (int) x;
        var yi = (int) y;
        var dx = x - (double) xi;
        var dy = y - (double) yi;
        return lerp(lerp(gradient(hashFunc(xi, yi), dx, dy),
                         gradient(hashFunc(xi + 1, yi), dx - 1, dy),
                         fade(dx)),
                    lerp(gradient(hashFunc(xi, yi + 1), dx, dy - 1),
                         gradient(hashFunc(xi + 1, yi + 1), dx - 1, dy - 1),
                         fade(dx)),
                    fade(dy));
    }
}
