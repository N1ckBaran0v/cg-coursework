package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

public class Camera extends SceneObject {
    private final Vector4D vx = new Vector4D(1, 0, 0);
    private final Vector4D vy = new Vector4D(0, 1, 0);
    private final Vector4D vz = new Vector4D(0, 0, 1);
    private double focus = 512;
    private static final double EPS = 5e-7;
    private static final double MAX_ANGLE = 90;
    private static final double CIRCLE = 360;
    private double angleY, angleZ;

    @Override
    public void move(double dx, double dy, double dz) {
        var offset = new Vector4D(dx, 0, dz);
        var tm = getInverseMatrix();
        tm.transformVector(offset);
        var center = getCenter();
        center.add(offset);
        center.add(0, dy, 0);
    }

    @Override
    public void rotate(double ax, double ay, double az) {
        angleY += ay;
        if (angleY > MAX_ANGLE) {
            angleY = MAX_ANGLE;
        } else if (angleY < -MAX_ANGLE) {
            angleY = -MAX_ANGLE;
        }
        angleZ += az;
        if (angleZ >= CIRCLE) {
            angleZ -= CIRCLE;
        } else if (angleZ <= -CIRCLE) {
            angleZ += CIRCLE;
        }
        var ray = Math.toRadians(angleY);
        var raz = Math.toRadians(angleZ);
        var cosAlpha = Math.cos(raz);
        var sinAlpha = Math.sin(raz);
        var cosBetta = Math.cos(ray);
        var sinBetta = Math.sin(ray);
        vx.set(cosAlpha, 0, -sinAlpha);
        vy.set(-sinBetta * sinAlpha, cosBetta, -sinBetta * cosAlpha);
        vz.set(sinAlpha * cosBetta, sinBetta, cosAlpha * cosBetta);
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

    private Matrix4D getInverseMatrix() {
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
