package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;
import org.jetbrains.annotations.NotNull;

class CalculateNormalsVisitor implements SceneObjectVisitor {
    @Override
    public void visit(@NotNull PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            for (var dot : polygon) {
                dot.normal.add(polygon.normal);
            }
        }
    }
}
