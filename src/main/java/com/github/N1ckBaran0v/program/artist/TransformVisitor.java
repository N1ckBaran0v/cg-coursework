package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.geometry.*;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TransformVisitor implements SceneObjectVisitor {
    private final List<Polygon3D> polygons = new ArrayList<>();
    private final Matrix4D transformMatrix;
    private final Dot4D center;
    private final double x0, y0, x1, y1;
    private final Map<Dot4D, Dot4D> transformed = new HashMap<>();
    private final Map<Dot4D, Dot3D> formatted = new HashMap<>();
    private final List<Dot4D> invalid = new ArrayList<>();
    private final List<Dot4D> maybeVisible = new ArrayList<>();
    private List<Dot3D> currList = new ArrayList<>();
    private List<Dot3D> prevList = new ArrayList<>();
    private static final int BY_X = 0;
    private static final int BY_Y = 1;

    public TransformVisitor(Camera camera, AbstractImage image) {
        transformMatrix = camera.getTransformMatrix();
        center = camera.getCenter();
        var width = image.getWidth();
        var height = image.getHeight();
        x0 = width / 2;
        y0 = height / 2;
        x1 = x0 - width;
        y1 = y0 - height;
    }

    @Override
    public void visit(PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            for (var elem : polygon) {
                var dot = transformed.get(elem);
                if (dot == null) {
                    var vec = new Vector4D(center, elem);
                    transformMatrix.transformVector(vec);
                    dot = new Dot4D(vec);
                    transformed.put(elem, dot);
                }
                if (dot.x >= 0) {
                    maybeVisible.add(dot);
                } else {
                    invalid.add(dot);
                }
            }
            var len = maybeVisible.size();
            for (var incorrect : invalid) {
                for (var dot : maybeVisible) {
                    maybeVisible.add(len, findDot4D(incorrect, dot));
                }
            }
            for (var dot : maybeVisible) {
                var formattedDot = formatted.get(dot);
                if (formattedDot == null) {
                    formattedDot = dot.getDot3D();
                    formatted.put(dot, formattedDot);
                }
                currList.add(formattedDot);
            }
            cut();
            triangulate(currList, polygon.color);
            invalid.clear();
            maybeVisible.clear();
            currList.clear();
            prevList.clear();
        }
    }

    private void cut() {
        if (currList.isEmpty()) {
            return;
        }
        prevList.clear();
        var tmp = prevList;
        prevList = currList;
        currList = tmp;
        var prev = prevList.getLast();
        for (var curr : prevList) {
            if (prev.x > x0) {
                if (curr.x <= x0) {
                    currList.add(findDot3D(prev, curr, x0, BY_X));
                }
            } else {
                currList.add(prev);
            }
            prev = curr;
        }
        if (currList.isEmpty()) {
            return;
        }
        prevList.clear();
        tmp = prevList;
        prevList = currList;
        currList = tmp;
        prev = prevList.getLast();
        for (var curr : prevList) {
            if (prev.x < x1) {
                if (curr.x >= x1) {
                    currList.add(findDot3D(prev, curr, x1, BY_X));
                }
            } else {
                currList.add(prev);
            }
            prev = curr;
        }

        if (currList.isEmpty()) {
            return;
        }
        prevList.clear();
        tmp = prevList;
        prevList = currList;
        currList = tmp;
        prev = prevList.getLast();
        for (var curr : prevList) {
            if (prev.y > y0) {
                if (curr.y <= y0) {
                    currList.add(findDot3D(prev, curr, y0, BY_Y));
                }
            } else {
                currList.add(prev);
            }
            prev = curr;
        }
        if (currList.isEmpty()) {
            return;
        }
        prevList.clear();
        tmp = prevList;
        prevList = currList;
        currList = tmp;
        prev = prevList.getLast();
        for (var curr : prevList) {
            if (prev.y < y1) {
                if (curr.y >= y1) {
                    currList.add(findDot3D(prev, curr, y1, BY_Y));
                }
            } else {
                currList.add(prev);
            }
            prev = curr;
        }
    }

    private Dot3D findDot3D(Dot3D a, Dot3D b, double coord, int axis) {
        Dot3D result;
        switch (axis) {
            case BY_X:
                var t = (a.x - coord) / (a.x - b.x);
                result = new Dot3D(coord, a.y + (b.y - a.y) * t, a.z + (b.z - a.z) * t);
                break;
            case BY_Y:
                var p = (a.y - coord) / (a.y - b.y);
                result = new Dot3D(a.x + (b.x - a.x) * p, coord, a.z + (b.z - a.z) * p);
                break;
            default:
                return null;
        }
        return result;
    }

    private Dot4D findDot4D(Dot4D a, Dot4D b) {
        var t = a.x / (a.x - b.x);
        return new Dot4D(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t, 0);
    }

    private void triangulate(List<Dot3D> polygon, Color color) {
        if (polygon.size() < 3) {
            return;
        }
        var first = polygon.getFirst();
        var second = polygon.get(1);
        for (var i = 2; i < polygon.size(); ++i) {
            var third = polygon.get(i);
            polygons.add(new Polygon3D(first, second, third, color));
            second = third;
        }
    }

    public List<Polygon3D> getPolygons() {
        return polygons;
    }
}
