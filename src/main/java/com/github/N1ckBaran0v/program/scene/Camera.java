package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

public class Camera extends SceneObject {
    private final Vector4D vx = new Vector4D(0, 1, 0);
    private final Vector4D vy = new Vector4D(0, 0, 1);
    private final Vector4D vz = new Vector4D(1, 0, 0);
    private double focus = 1024;

    @Override
    public void move(double dx, double dy, double dz) {
        var offset = new Vector4D(dx, dy, dz);
        var tm = getTransformMatrix();
        tm.transformVector(offset);
        System.out.println(offset.x + " " + offset.y + " " + offset.z);
        var center = getCenter();
        center.copy(new Dot4D(center, offset));
    }

//    @Override
//    public void rotate(double ax, double ay, double az) {
//        var tmy = Matrix4D.getRotateMatrix(0, ay, 0);
//        tmy.transformVector(vx);
//        tmy.transformVector(vz);
//    }

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
        var center = getCenter();
        tm.xw = -center.x;
        tm.yw = -center.y;
        tm.zw = -center.z;
        return tm;
    }
}
