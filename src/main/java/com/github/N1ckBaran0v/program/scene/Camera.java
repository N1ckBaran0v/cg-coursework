package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Dot4D;
import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

public class Camera extends SceneObject {
    private final Vector4D vx = new Vector4D(1, 0, 0);
    private final Vector4D vy = new Vector4D(0, 1, 0);
    private final Vector4D vz = new Vector4D(0, 0, 1);
    private double focus = 1024;

    @Override
    public void move(double dx, double dy, double dz) {
        var offset = new Vector4D(dx, dy, dz);
        var tm = new Matrix4D();
        configureMatrix(tm);
        tm.transformVector(offset);
        var center = getCenter();
        center.copy(new Dot4D(center, offset));
    }

    @Override
    public void rotate(double ax, double ay, double az) {
        var tmy = Matrix4D.getRotateMatrix(0, ay, 0);
        tmy.transformVector(vx);
        tmy.transformVector(vz);
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
        var result = new Matrix4D();
        configureMatrix(result);
        var matrix = result.matrix;
        matrix[3][2] = 1 / focus;
        return result;
    }

    private void configureMatrix(Matrix4D transformMatrix) {
        var matrix = transformMatrix.matrix;
        matrix[0][0] = vx.x;
        matrix[1][0] = vx.y;
        matrix[2][0] = vx.z;
        matrix[0][1] = vy.x;
        matrix[1][1] = vy.y;
        matrix[2][1] = vy.z;
        matrix[0][2] = vz.x;
        matrix[1][2] = vz.y;
        matrix[2][2] = vz.z;
    }
}
