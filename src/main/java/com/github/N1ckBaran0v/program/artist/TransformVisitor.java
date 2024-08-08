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
//        System.out.println("Start to model:");
        for (var polygon : polygonalModel) {
//            System.out.println("Start polygon");
            for (var elem : polygon) {
//                System.out.println("Before: " + elem.x + " " + elem.y + " " + elem.z + " " + elem.w);
                var dot = transformed.get(elem);
                if (dot == null) {
                    var vec = new Vector4D(center, elem);
                    transformMatrix.transformVector(vec);
                    dot = new Dot4D(vec);
                    transformed.put(elem, dot);
                }
//                System.out.println("After: " + dot.x + " " + dot.y + " " + dot.z + " " + dot.w);
                if (dot.z >= 0) {
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
        System.out.println("\n\n\n");
    }

    private Dot4D findDot(Dot4D a, Dot4D b) {
        var result = intersection.get(a, b);
        if (result == null) {
            var t = a.z / (a.z - b.z);
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
