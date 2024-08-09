package com.github.N1ckBaran0v.program.scene;

public interface SceneObjectVisitor {
    default void visit(Camera camera) {
    }

    default void visit(PolygonalModel polygonalModel) {
    }
}
