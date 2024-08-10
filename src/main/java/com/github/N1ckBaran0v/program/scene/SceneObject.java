package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Vector4D;

import javax.naming.OperationNotSupportedException;

abstract public class SceneObject {
    private final Dot4D center;

    protected SceneObject() {
        this.center = new Dot4D();
    }

    protected SceneObject(Dot4D center) {
        this.center = center;
    }

    public void move(double dx, double dy, double dz) {
        center.copy(new Dot4D(center, new Vector4D(dx, dy, dz)));
    }

    public void rotate(double ax, double ay, double az) {
        throw new RuntimeException(new OperationNotSupportedException());
    }

    public Dot4D getCenter() {
        return center;
    }

    abstract public void accept(SceneObjectVisitor visitor);
}
