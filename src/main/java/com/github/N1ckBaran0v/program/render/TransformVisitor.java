package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.math.*;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class TransformVisitor implements SceneObjectVisitor {
    private final Vector3D center;
    private final AbstractDrawStrategy drawStrategy;
    private final Matrix4D cameraMatrix, frustumMatrix;
    private final double focus;
    private final List<DrawVector> invalid = new ArrayList<>(3);
    private final List<DrawVector> maybeVisible = new ArrayList<>(4);

    public TransformVisitor(@NotNull Camera camera, @NotNull DrawStrategyCreator drawStrategyCreator,
                            @NotNull AbstractImage image, @NotNull Color color) {
        center = camera.getCenter();
        focus = camera.getFocus();
        drawStrategy = drawStrategyCreator.create(image, color);
        cameraMatrix = camera.getTransformMatrix();
        var frustum = new Frustum(image, focus, camera.getVisibility());
        frustumMatrix = frustum.getTransformMatrix();
    }

    @Override
    public void visit(@NotNull PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            drawPolygon(polygon);
        }
    }

    private void drawPolygon(Polygon polygon) {
        for (var elem : polygon) {
            var dot = elem.drawDot;
            if (!dot.isUsed) {
                dot.set(center, elem.realDot);
                cameraMatrix.transformVector(dot);
                frustumMatrix.transformVector(dot);
                if (dot.w >= focus) {
                    maybeVisible.add(dot);
                    dot.to3D();
                } else {
                    invalid.add(dot);
                }
                dot.isUsed = true;
            } else {
                if (dot.w >= focus) {
                    maybeVisible.add(dot);
                } else {
                    invalid.add(dot);
                }
            }
        }
        var len = maybeVisible.size();
        for (var incorrect : invalid) {
            for (var i = 0; i < len; ++i) {
                maybeVisible.add(len, findDot(incorrect, maybeVisible.get(i)));
            }
        }
        triangulate(maybeVisible, polygon.color);
        invalid.clear();
        maybeVisible.clear();
    }

    private DrawVector findDot(DrawVector a, DrawVector b) {
        var t = (b.w - focus) / (b.w - a.w);
        var result = new DrawVector();
        var x = b.x * b.w;
        result.x = x + (a.x - x) * t;
        var y = b.y * b.w;
        result.y = y + (a.y - y) * t;
        result.w = focus;
        result.to3D();
        result.isUsed = true;
        result.brightness = b.brightness + (a.brightness - b.brightness) * t;
        return result;
    }

    private void triangulate(List<DrawVector> polygon, Color color) {
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
