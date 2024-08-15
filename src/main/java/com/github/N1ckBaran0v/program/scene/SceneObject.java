package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Vector4D;

import javax.naming.OperationNotSupportedException;

abstract public class SceneObject {
    private final transient Vector4D center;
    private transient boolean needRecalculate;

    protected SceneObject() {
        this.center = new Vector4D();
    }

    protected SceneObject(Vector4D center) {
        this.center = center;
    }

    public void move(double dx, double dy, double dz) {
        center.add(dx, dy, dz);
    }

    public void rotate(double ax, double ay, double az) {
        throw new RuntimeException(new OperationNotSupportedException());
    }

    public void setPosition(double x, double y, double z) {
        center.set(x, y, z);
    }

    public Vector4D getCenter() {
        return center;
    }

    abstract public void accept(SceneObjectVisitor visitor);

    public boolean isNeedRecalculate() {
        return needRecalculate;
    }

    public void setNeedRecalculate(boolean needRecalculate) {
        this.needRecalculate = needRecalculate;
    }
}
