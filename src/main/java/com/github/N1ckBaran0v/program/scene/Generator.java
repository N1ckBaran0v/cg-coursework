package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.HashMap2D;
import com.github.N1ckBaran0v.program.containers.Map2D;
import com.github.N1ckBaran0v.program.math.Gauss;
import com.github.N1ckBaran0v.program.math.Polygon;
import com.github.N1ckBaran0v.program.math.PolygonVector;
import com.github.N1ckBaran0v.program.math.Vector3D;
import com.github.N1ckBaran0v.program.render.Color;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class Generator extends Thread {
    private static boolean needStop = false;
    private final Landscape landscape;
    private final List<List<Double>> heights;
    private final int sideSize, squareSize, step, size;
    private final double[][] fastHeights, crigingMatrix;
    private final double radius2;

    public Generator(@NotNull Landscape landscape, @NotNull List<List<Double>> heights, int sideSize, int squareSize,
                     int step) {
        if (sideSize <= 0 || squareSize <= 0 || step <= 0) {
            throw new IllegalArgumentException();
        }
        if (sideSize % squareSize != 0 || squareSize % step != 0) {
            throw new IllegalArgumentException();
        }
        size = heights.size();
        if (size != sideSize / squareSize + 1) {
            throw new IllegalArgumentException();
        }
        for (var arr : heights) {
            if (arr.size() != size) {
                throw new IllegalArgumentException();
            }
        }
        this.landscape = landscape;
        this.heights = heights;
        this.sideSize = sideSize;
        this.squareSize = squareSize;
        this.step = step;
        needStop = true;
        fastHeights = new double[size][size];
        for (var i = 0; i < size; ++i) {
            for (var j = 0; j < size; ++j) {
                fastHeights[i][j] = heights.get(i).get(j);
            }
        }
        radius2 = 2 * sideSize * sideSize;
        var dotsCount = size * size;
        crigingMatrix = new double[dotsCount][dotsCount];
        for (var i = 0; i < dotsCount; ++i) {
            for (var j = 0; j < dotsCount; ++j) {
                crigingMatrix[i][j] = covariation((i / size) * squareSize, (i % size) * squareSize,
                        (j / size) * squareSize, (j % size) * squareSize);
            }
        }
    }

    @Override
    public void run() {
        synchronized (landscape) {
            needStop = false;
            landscape.getPolygons().clear();
            var map = generateHeights();
            var polygons = generatePolygons(map);
            if (!needStop) {
                landscape.setPolygons(polygons);
                landscape.setInputHeightsMap(heights);
                landscape.setSideSize(sideSize);
                landscape.setSquareSize(squareSize);
                landscape.setStep(step);
                landscape.setNeedRecalculate(true);
            }
        }
    }

    private Map2D<Integer, PolygonVector> generateHeights() {
        var map = new HashMap2D<Integer, PolygonVector>();
        for (var i = 0; i <= sideSize && !needStop; i += step) {
            for (var j = 0; j <= sideSize && !needStop; j += step) {
                map.put(i, j, new PolygonVector(i, interpolate(i, j), j));
            }
        }
        return map;
    }

    private List<Polygon> generatePolygons(@NotNull Map2D<Integer, PolygonVector> heights) {
        var polygons = new LinkedList<Polygon>();
        for (var i = 0; i < sideSize && !needStop; i += step) {
            var near = heights.get(i, 0);
            var dx = heights.get(i + step, 0);
            var dz = near;
            var far = dx;
            for (var j = 0; j < sideSize && !needStop; j += step) {
                far = heights.get(i + step, j + step);
                dz = heights.get(i, j + step);
                var normal1 = Vector3D.getNormal(near, dx, far);
                if (normal1.y < 0) {
                    normal1.inverse();
                }
                var normal2 = Vector3D.getNormal(near, dz, far);
                if (normal2.y < 0) {
                    normal2.inverse();
                }
                normal1.normalize();
                normal2.normalize();
                polygons.add(new Polygon(near, dx, far, normal1, new Color(0, 255, 0)));
                polygons.add(new Polygon(near, dz, far, normal2, new Color(0, 255, 0)));
                near = dz;
                dx = far;
            }
        }
        return polygons;
    }

    private double interpolate(int x, int y) {
        var result = 0.0;
        if (x % squareSize + y % squareSize == 0) {
            result = fastHeights[x / squareSize][y / squareSize];
        } else {
            var matrix = copy(crigingMatrix);
            var answer = new double[matrix.length];
            var xi = 0;
            var yi = 0;
            for (var k = 0; k < matrix.length; ++k) {
                answer[k] = covariation(xi, yi, x, y);
                if (yi == sideSize) {
                    yi = 0;
                    xi += squareSize;
                } else {
                    yi += squareSize;
                }
            }
            Gauss.solve(matrix, answer);
            var i = 0;
            var j = 0;
            for (var k = 0; k < matrix.length; ++k) {
                result += answer[k] * fastHeights[i][j];
                ++j;
                if (j == size) {
                    j = 0;
                    ++i;
                }
            }
        }
        return result;
    }

    private double covariation(int x0, int y0, int x1, int y1) {
        var h2 = (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0);
        return 1 - Math.exp(-Math.sqrt(h2/radius2));
    }

    private double[][] copy(double[][] matrix) {
        var result = new double[matrix.length][matrix.length];
        for (var i = 0; i < matrix.length; ++i) {
            System.arraycopy(matrix[i], 0, result[i], 0, matrix.length);
        }
        return result;
    }
}