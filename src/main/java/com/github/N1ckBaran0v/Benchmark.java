package com.github.N1ckBaran0v;

import com.github.N1ckBaran0v.program.scene.Generator;
import com.github.N1ckBaran0v.program.scene.Landscape;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    private static final int ITERATIONS = 100;

    private Benchmark() {
    }

    public static void benchmark() {
        var threadMXBean = ManagementFactory.getThreadMXBean();
        var defaultSideSize = 5000;
        var defaultSquareSize = 1000;
        var defaultStep = 100;
        var sideSizes = new int[]{1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        var squareSizes = new int[]{100, 500, 1000, 2500, 5000};
        var steps = new int[]{50, 100, 250, 500, 1000};
        var landscape = new Landscape();
        // sideSize
        System.out.println("Side size benchmark");
        var results1 = new double[sideSizes.length];
        var index1 = 0;
        for (var sideSize : sideSizes) {
            var size = sideSize / defaultSquareSize + 1;
            var dots = new ArrayList<List<Double>>(size);
            var arr = new ArrayList<Double>(size);
            for (var i = 0; i < size; ++i) {
                arr.add(0.0);
                dots.add(arr);
            }
            for (var i = 0; i < ITERATIONS; ++i) {
                System.out.printf("%d [%d/%d]\t\r", sideSize, i, ITERATIONS);
                var start = threadMXBean.getCurrentThreadCpuTime();
                new Generator(landscape, dots, sideSize, defaultSquareSize, defaultStep).run();
                results1[index1] += threadMXBean.getCurrentThreadCpuTime() - start;
            }
            System.out.printf("%d [%d/%d]\t\r", sideSize, ITERATIONS, ITERATIONS);
            ++index1;
        }
        outputResult(results1);
        // squareSize
        System.out.println("Square size benchmark");
        var results2 = new double[squareSizes.length];
        var index2 = 0;
        for (var squareSize : squareSizes) {
            var size = defaultSideSize / squareSize + 1;
            var dots = new ArrayList<List<Double>>(size);
            var arr = new ArrayList<Double>(size);
            for (var i = 0; i < size; ++i) {
                arr.add(0.0);
                dots.add(arr);
            }
            for (var i = 0; i < ITERATIONS; ++i) {
                System.out.printf("%d [%d/%d]\t\r", squareSize, i, ITERATIONS);
                var start = threadMXBean.getCurrentThreadCpuTime();
                new Generator(landscape, dots, defaultSideSize, squareSize, defaultStep).run();
                results2[index2] += threadMXBean.getCurrentThreadCpuTime() - start;
            }
            System.out.printf("%d [%d/%d]\t\r", squareSize, ITERATIONS, ITERATIONS);
            ++index2;
        }
        outputResult(results2);
        // step
        System.out.println("Step benchmark");
        var results3 = new double[squareSizes.length];
        var index3 = 0;
        var size = defaultSideSize / defaultSquareSize + 1;
        var dots = new ArrayList<List<Double>>(size);
        var arr = new ArrayList<Double>(size);
        for (var i = 0; i < size; ++i) {
            arr.add(0.0);
            dots.add(arr);
        }
        for (var step : steps) {
            for (var i = 0; i < ITERATIONS; ++i) {
                System.out.printf("%d [%d/%d]\t\r", step, i, ITERATIONS);
                var start = threadMXBean.getCurrentThreadCpuTime();
                new Generator(landscape, dots, defaultSideSize, defaultSquareSize, step).run();
                results3[index3] += threadMXBean.getCurrentThreadCpuTime() - start;
            }
            System.out.printf("%d [%d/%d]\t\r", step, ITERATIONS, ITERATIONS);
            ++index3;
        }
        outputResult(results3);
    }

    private static void outputResult(double[] results) {
        System.out.print("Results: [");
        for (var i = 0; i < results.length; ++i) {
            if (i + 1 != results.length) {
                System.out.printf("%.9f, ", results[i] / (ITERATIONS * 1e9));
            } else {
                System.out.printf("%.9f]\n", results[i] / (ITERATIONS * 1e9));
            }
        }
    }
}
