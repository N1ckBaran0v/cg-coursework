package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Axis;
import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

public class Camera extends SceneObject {
    private final Vector4D vx = new Vector4D(0, 1, 0);
    private final Vector4D vy = new Vector4D(0, 0, 1);
    private final Vector4D vz = new Vector4D(1, 0, 0);
    private double focus = 512;

    @Override
    public void move(double dx, double dy, double dz) {
        var offset = new Vector4D(dx, dy, dz);
        var tm = getInverseMatrix();
        tm.transformVector(offset);
        getCenter().add(offset);
    }

    @Override
    public void rotate(double ax, double ay, double az) {
        var tmy = Matrix4D.getRotateMatrix(az, Axis.OZ);
        tmy.transformVector(vx);
        tmy.transformVector(vy);
        tmy.transformVector(vz);
        vx.normalize();
        vy.normalize();
        vz.normalize();
    }

    @Override
    public void accept(SceneObjectVisitor visitor) {
        visitor.visit(this);
    }

    public double getFocus() {
        return focus;
    }

    public void setFocus(double focus) {
        this.focus = focus;
    }

    public Matrix4D getTransformMatrix() {
        var tm = new Matrix4D();
        tm.xx = vx.x;
        tm.xy = vx.y;
        tm.xz = vx.z;
        tm.yx = vy.x;
        tm.yy = vy.y;
        tm.yz = vy.z;
        tm.zx = vz.x;
        tm.zy = vz.y;
        tm.zz = vz.z;
        return tm;
    }

    public Matrix4D getInverseMatrix() {
        var tm = new Matrix4D();
        tm.xx = vx.x;
        tm.yx = vx.y;
        tm.zx = vx.z;
        tm.xy = vy.x;
        tm.yy = vy.y;
        tm.zy = vy.z;
        tm.xz = vz.x;
        tm.yz = vz.y;
        tm.zz = vz.z;
        return tm;
    }
}
