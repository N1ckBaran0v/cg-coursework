package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.containers.HashMap2D;
import com.github.N1ckBaran0v.program.containers.Map2D;
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
    private final Matrix4D transformMatrix;
    private final Dot4D center;
    private final AbstractDrawStrategy drawStrategy;
    private final Map<Dot4D, Dot4D> transformed = new HashMap<>();
    private final Map<Dot4D, Dot3D> formatted = new HashMap<>();
    private final Map2D<Dot4D, Dot4D> intersection = new HashMap2D<>();
    private final List<Dot4D> invalid = new ArrayList<>();
    private final List<Dot4D> maybeVisible = new ArrayList<>();
    private final List<Dot3D> dotList = new ArrayList<>();

    public TransformVisitor(Camera camera, AbstractImage image, DrawStrategyCreator drawStrategyCreator) {
        transformMatrix = camera.getTransformMatrix();
        center = camera.getCenter();
        drawStrategy = drawStrategyCreator.create(image);
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
                    maybeVisible.add(len, findDot(incorrect, dot));
                }
            }
            for (var dot : maybeVisible) {
                var formattedDot = formatted.get(dot);
                if (formattedDot == null) {
                    formattedDot = dot.getDot3D();
                    formatted.put(dot, formattedDot);
                }
                dotList.add(formattedDot);
            }
            triangulate(dotList, polygon.color);
            invalid.clear();
            maybeVisible.clear();
            dotList.clear();
        }
    }

    private Dot4D findDot(Dot4D a, Dot4D b) {
        var result = intersection.get(a, b);
        if (result == null) {
            var t = a.x / (a.x - b.x);
            result = new Dot4D(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t, 0);
            intersection.put(a, b, result);
        }
        return result;
    }

    private void triangulate(List<Dot3D> polygon, Color color) {
        if (polygon.size() < 3) {
            return;
        }
        var first = polygon.getFirst();
        var second = polygon.get(1);
        for (var i = 2; i < polygon.size(); ++i) {
            var third = polygon.get(i);
            drawStrategy.draw(first, second, third, color);
            second = third;
        }
    }
}
