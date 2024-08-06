package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Dot4D;
import com.github.N1ckBaran0v.program.geometry.Matrix4D;

abstract public class SceneObject {
    private final Dot4D center;

    protected SceneObject() {
        this.center = new Dot4D();
    }

    protected SceneObject(Dot4D center) {
        this.center = center;
    }

    abstract public void transform(Matrix4D transformMatrix);

    public Dot4D getCenter() {
        return center;
    }

    abstract public void accept(SceneObjectVisitor visitor);
}
