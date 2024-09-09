package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.PolygonVector;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

import java.util.HashSet;
import java.util.Set;

class CollectDotsVisitor implements SceneObjectVisitor {
    private final Set<PolygonVector> dots = new HashSet<>();

    @Override
    public void visit(PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            for (var dot : polygon) {
                dots.add(dot);
            }
        }
    }

    public Set<PolygonVector> getDots() {
        return dots;
    }
}
