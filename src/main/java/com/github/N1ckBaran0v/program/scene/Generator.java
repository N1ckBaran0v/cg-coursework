package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.HashMap2D;
import com.github.N1ckBaran0v.program.containers.Map2D;
import com.github.N1ckBaran0v.program.geometry.*;

import java.util.ArrayList;
import java.util.List;

class Generator {
    private Map2D<Long, PolygonVector> dotsMap;
    private double minHeight, difference;
    private PerlinNoise generator;
    private long sideSize, step;
    private boolean isGenerates;

    public void setParams(Landscape landscape) {
        generator = new PerlinNoise(landscape.getSeed(), sideSize);
        minHeight = landscape.getMinHeight();
        difference = landscape.getMaxHeight() - minHeight;
        sideSize = landscape.getSideSize();
        step = landscape.getStep();
        dotsMap = new HashMap2D<>();
    }

    public Chunk generateChunk(long i, long j) {
        var x0 = i * sideSize;
        var z0 = j * sideSize;
        var x1 = x0 + sideSize;
        var z1 = z0 + sideSize;
        return new Chunk(generatePolygons(x0, z0, x1, z1));
    }

    public void removeChunk(Map2D<Long, Chunk> chunks, long i, long j) {
        var x0 = i * sideSize;
        var z0 = j * sideSize;
        var x1 = x0 + sideSize;
        var z1 = z0 + sideSize;
        for (var x = x0 + step; x < x1; x += step) {
            for (var z = z0 + step; z < z1; z += step) {
                dotsMap.remove(x, z);
            }
        }
        if (!chunks.contains(i - 1, j)) {
            for (var z = z0 + step; z < z1; z += step) {
                dotsMap.remove(x0, z);
            }
        }
        if (!chunks.contains(i + 1, j)) {
            for (var z = z0 + step; z < z1; z += step) {
                dotsMap.remove(x1, z);
            }
        }
        if (!chunks.contains(i, j - 1)) {
            for (var x = x0 + step; x < x1; x += step) {
                dotsMap.remove(x, z0);
            }
        }
        if (!chunks.contains(i, j + 1)) {
            for (var x = x0 + step; x < x1; x += step) {
                dotsMap.remove(x, z1);
            }
        }
        if (!chunks.contains(i - 1, j) && !chunks.contains(i, j - 1) && !chunks.contains(i - 1, j - 1)) {
            dotsMap.remove(x0, z0);
        }
        if (!chunks.contains(i - 1, j) && !chunks.contains(i, j + 1) && !chunks.contains(i - 1, j + 1)) {
            dotsMap.remove(x0, z1);
        }
        if (!chunks.contains(i + 1, j) && !chunks.contains(i, j - 1) && !chunks.contains(i + 1, j - 1)) {
            dotsMap.remove(x1, z0);
        }
        if (!chunks.contains(i + 1, j) && !chunks.contains(i, j + 1) && !chunks.contains(i + 1, j + 1)) {
            dotsMap.remove(x1, z1);
        }
    }

    private PolygonVector getDot(long x, long z) {
        var dot = dotsMap.get(x, z);
        if (dot == null) {
            var y = generator.get(x, z) * difference + minHeight;
            dot = new PolygonVector(x, y, z);
            dotsMap.put(x, z, dot);
        }
        return dot;
    }

    private List<Polygon> generatePolygons(long x0, long z0, long x1, long z1) {
        var result = new ArrayList<Polygon>();
        for (var x = x0; x < x1; x += step) {
            var xnext = x + step;
            for (var z = z0; z < z1; z += step) {
                var znext = z + step;
                var near = getDot(x, z);
                var far = getDot(xnext, znext);
                var dx = getDot(xnext, z);
                var dz = getDot(x, znext);
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
                result.add(new Polygon(near, dx, far, normal1, new Color(0, 255, 0)));
                result.add(new Polygon(near, dz, far, normal2, new Color(0, 255, 0)));
            }
        }
        return result;
    }

    public boolean isGenerates() {
        return isGenerates;
    }

    public void setGenerates(boolean generates) {
        isGenerates = generates;
    }
}
