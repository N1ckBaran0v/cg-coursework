package com.github.N1ckBaran0v.program.scene;

import org.jetbrains.annotations.NotNull;

public interface SceneObjectVisitor {
    default void visit(@NotNull Camera camera) {
    }

    default void visit(@NotNull PolygonalModel polygonalModel) {
    }

    default void visit(@NotNull Composite composite) {
        for (var obj : composite) {
            obj.accept(this);
        }
    }

    default void visit(@NotNull FarLight light) {
    }
}
