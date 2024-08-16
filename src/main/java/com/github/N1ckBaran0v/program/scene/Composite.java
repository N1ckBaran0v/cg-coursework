package com.github.N1ckBaran0v.program.scene;

public abstract class Composite extends SceneObject implements Iterable<SceneObject> {
    @Override
    public void accept(SceneObjectVisitor visitor) {
        visitor.visit(this);
    }
}
