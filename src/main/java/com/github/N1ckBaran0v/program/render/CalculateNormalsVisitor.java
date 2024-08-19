package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Vector4D;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

import java.util.HashMap;
import java.util.Map;

class CalculateNormalsVisitor implements SceneObjectVisitor {
    private final Map<Vector4D, Vector4D> normals = new HashMap<>();
    private final Map<Vector4D, Integer> counts = new HashMap<>();

    @Override
    public void visit(PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            for (var dot : polygon) {
                add(dot, polygon.normal);
            }
        }
    }

    private void add(Vector4D dot, Vector4D normal) {
        var tmp = normals.get(dot);
        if (tmp == null) {
            tmp = new Vector4D();
            normals.put(dot, tmp);
        }
        tmp.add(normal);
        counts.put(dot, counts.getOrDefault(dot, 0) + 1);
    }

    public Map<Vector4D, Vector4D> getNormals() {
        return normals;
    }
}
