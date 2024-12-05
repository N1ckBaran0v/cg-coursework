package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.math.Vector3D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    private Landscape landscape;
    private List<List<Double>> heights;
    private int sideSize, squareSize, step;

    @BeforeEach
    void setUp() {
        landscape = new Landscape();
        heights = new ArrayList<>();
        sideSize = 8;
        squareSize = 4;
        step = 2;
        for (var i = 0; i < 3; ++i) {
            var arr = new ArrayList<Double>();
            for (var j = 0; j < 3; ++j) {
                arr.add(1.0);
            }
            heights.add(arr);
        }
    }

    @Test
    void negativeHeights1() {
        try {
            heights.removeLast();
            new Generator(landscape, heights, sideSize, squareSize, step);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void negativeHeights2() {
        try {
            heights.getLast().removeLast();
            new Generator(landscape, heights, sideSize, squareSize, step);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void negativeHeights3() {
        try {
            new Generator(landscape, heights, sideSize << 1, squareSize, step);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void negativeSideSize() {
        try {
            new Generator(landscape, heights, 0, squareSize, step);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void negativeSquareSize() {
        try {
            new Generator(landscape, heights, sideSize, 0, step);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void negativeStep() {
        try {
            new Generator(landscape, heights, sideSize, squareSize, 0);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void run() {
        try {
            var generator = new Generator(landscape, heights, sideSize, squareSize, step);
            generator.start();
            generator.join();
            var result = new HashSet<Vector3D>();
            for (var i = 0; i <= sideSize; i += step) {
                for (var j = 0; j <= sideSize; j += step) {
                    result.add(new Vector3D(i, 1.0, j));
                }
            }
            for (var polygon : landscape) {
                for (var dot : polygon) {
                    assertTrue(result.contains(dot.realDot));
                }
            }
        } catch (IllegalArgumentException | InterruptedException e) {
            fail();
        }
    }
}