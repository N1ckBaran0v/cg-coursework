package com.github.N1ckBaran0v.program.scene;

import org.jetbrains.annotations.NotNull;

public abstract class Composite extends SceneObject implements Iterable<SceneObject> {
    @Override
    public void accept(@NotNull SceneObjectVisitor visitor) {
        visitor.visit(this);
    }
}
