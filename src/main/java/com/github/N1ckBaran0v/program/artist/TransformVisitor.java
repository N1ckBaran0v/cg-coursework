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
    private final Vector4D center;
    private final AbstractDrawStrategy drawStrategy;
    private final Matrix4D cameraMatrix, frustumMatrix;
    private final int width, height;
    private final double maxZ, halfWidth, halfHeight;
    private final Map<Vector4D, Vector4D> transformed = new HashMap<>();
    private final Map<Vector4D, Vector3D> formatted = new HashMap<>();
    private final Map2D<Vector4D, Vector4D> intersection = new HashMap2D<>();
    private final List<Vector4D> invalid = new ArrayList<>();
    private final List<Vector4D> maybeVisible = new ArrayList<>();
    private final List<Vector3D> dotList = new ArrayList<>();

    public TransformVisitor(Camera camera, DrawStrategyCreator drawStrategyCreator, AbstractImage image) {
        center = camera.getCenter();
        System.out.println("Center: " + center.x + " " + center.y + " " + center.z + " " + center.w);
        drawStrategy = drawStrategyCreator.create(image);
        cameraMatrix = camera.getTransformMatrix();
        width = image.getWidth();
        halfWidth = width / 2.0;
        height = image.getHeight();
        halfHeight = height / 2.0;
        var frustum = new Frustum(image, width / 2, width * 4);
        frustumMatrix = frustum.getTransformMatrix();
        var vec = new Vector4D(0, 0, -(width / 2));
        frustumMatrix.transformVector(vec);
        maxZ = vec.z;
    }

    @Override
    public void visit(PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            drawPolygon(polygon);
        }
        System.out.println("\n\n\n");
    }

    private void drawPolygon(Polygon4D polygon) {
        System.out.println("Start polygon");
        for (var elem : polygon) {
            System.out.println("Before: " + elem.x + " " + elem.y + " " + elem.z + " " + elem.w);
            var dot = transformed.get(elem);
            if (dot == null) {
                dot = Vector4D.sub(center, elem);
                System.out.println("Offset: " + dot.x + " " + dot.y + " " + dot.z + " " + dot.w);
                cameraMatrix.transformVector(dot);
                System.out.println("Transformed: " + dot.x + " " + dot.y + " " + dot.z + " " + dot.w);
                frustumMatrix.transformVector(dot);
                transformed.put(elem, dot);
            }
            System.out.println("After: " + dot.x + " " + dot.y + " " + dot.z + " " + dot.w);
            if (dot.z <= maxZ) {
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
            var t = a.z / (a.z - b.z);
            result = new Vector4D(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t, maxZ);
            result.w = a.w + (b.w - a.w) * t;
            intersection.put(a, b, result);
        }
        return result;
    }

    private Vector3D getFormattedDot(Vector4D dot) {
        var formattedDot = formatted.get(dot);
        if (formattedDot == null) {
            formattedDot = dot.toVector3D(halfWidth, halfHeight);
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
//            System.out.print("DrawPolygon: ");
//            System.out.print("(" + first.x + ", " + first.y + ", " + first.z + "), ");
//            System.out.print("(" + second.x + ", " + second.y + ", " + second.z + "), ");
//            System.out.print("(" + third.x + ", " + third.y + ", " + third.z + "), ");
//            System.out.println(color);
//            System.out.flush();
            drawStrategy.draw(first, second, third, color);
//            System.out.println();
            second = third;
        }
    }
}
