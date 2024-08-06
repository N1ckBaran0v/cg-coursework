package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Dot4D;
import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.geometry.Polygon4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;
import java.util.*;

public class ConvexObject extends PolygonalModel {
    private final List<Polygon4D> polygons = new ArrayList<>();
    private final Set<Dot4D> dots = new HashSet<>();

    @Override
    public void transform(Matrix4D transformMatrix) {
        var center = getCenter();
        for (var dot : dots) {
            var offset = new Vector4D(center, dot);
            transformMatrix.transformVector(offset);
            dot.copy(new Dot4D(dot, offset));
        }
        var offset = new Vector4D(center);
        transformMatrix.transformVector(offset);
        center.copy(new Dot4D(offset));
    }

    @Override
    public Iterator<Polygon4D> iterator() {
        return polygons.iterator();
    }
}
