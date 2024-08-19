package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.containers.HashMap2D;
import com.github.N1ckBaran0v.program.containers.Map2D;
import com.github.N1ckBaran0v.program.geometry.*;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

import java.util.*;

class TransformVisitor implements SceneObjectVisitor {
    private final Vector4D center;
    private final AbstractDrawStrategy drawStrategy;
    private final Matrix4D cameraMatrix, frustumMatrix;
    private final double focus;
    private final Map<Vector4D, Double> brightnesses;
    private final Map<Vector4D, Vector4D> transformed = new HashMap<>();
    private final Map<Vector4D, Vector4D> reverse = new HashMap<>();
    private final Map<Vector4D, Vector3D> formatted = new HashMap<>();
    private final Set<Vector4D> created = new HashSet<>();
    private final Map2D<Vector4D, Vector4D> intersection = new HashMap2D<>();
    private final List<Vector4D> invalid = new ArrayList<>();
    private final List<Vector4D> maybeVisible = new ArrayList<>();
    private final List<Vector3D> dotList = new ArrayList<>();

    public TransformVisitor(Camera camera, DrawStrategyCreator drawStrategyCreator, AbstractImage image, Color color,
                            Map<Vector4D, Double> brightnesses) {
        center = camera.getCenter();
        focus = camera.getFocus();
        this.brightnesses = brightnesses;
        drawStrategy = drawStrategyCreator.create(image, color);
        cameraMatrix = camera.getTransformMatrix();
        var frustum = new Frustum(image, focus, camera.getVisibility());
        frustumMatrix = frustum.getTransformMatrix();
    }

    @Override
    public void visit(PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            drawPolygon(polygon);
        }
    }

    public void removeCreated() {
        for (var dot : created) {
            brightnesses.remove(dot);
        }
    }

    private void drawPolygon(Polygon4D polygon) {
        for (var elem : polygon) {
            var dot = transformed.get(elem);
            if (dot == null) {
                dot = Vector4D.sub(center, elem);
                cameraMatrix.transformVector(dot);
                frustumMatrix.transformVector(dot);
                transformed.put(elem, dot);
                reverse.put(dot, elem);
            }
            if (dot.w >= focus) {
                maybeVisible.add(dot);
            } else {
                invalid.add(dot);
            }
        }
        var len = maybeVisible.size();
        for (var incorrect : invalid) {
            for (var i = 0; i < len; ++i) {
                maybeVisible.add(len, findDot(incorrect, maybeVisible.get(i)));
            }
        }
        for (var dot : maybeVisible) {
            dotList.add(getFormattedDot(dot));
        }
        triangulate(dotList, polygon.color);
        invalid.clear();
        maybeVisible.clear();
        dotList.clear();
    }

    private Vector4D findDot(Vector4D a, Vector4D b) {
        var result = intersection.get(a, b);
        if (result == null) {
            var t = (b.w - focus) / (b.w - a.w);
            result = new Vector4D(b.x + (a.x - b.x) * t, b.y + (a.y - b.y) * t, 0);
            result.w = focus;
            intersection.put(a, b, result);
            brightnesses.put(result, (brightnesses.get(reverse.get(a)) + brightnesses.get(reverse.get(b))) / 2);
            created.add(result);
        }
        return result;
    }

    private Vector3D getFormattedDot(Vector4D dot) {
        var formattedDot = formatted.get(dot);
        if (formattedDot == null) {
            formattedDot = dot.toVector3D(brightnesses.get(reverse.getOrDefault(dot, dot)));
            formatted.put(dot, formattedDot);
        }
        return formattedDot;
    }

    private void triangulate(List<Vector3D> polygon, Color color) {
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
