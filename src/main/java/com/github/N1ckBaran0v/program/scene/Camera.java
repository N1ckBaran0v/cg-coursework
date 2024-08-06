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
    public void transform(Matrix4D transformMatrix) {
        var matrix = transformMatrix.matrix;
        var offset = new Vector4D(matrix[0][3], matrix[1][3], matrix[2][3]);
        matrix[0][3] = matrix[1][3] = matrix[2][3] = 0;
        transformMatrix.transformVector(vx);
        transformMatrix.transformVector(vy);
        transformMatrix.transformVector(vz);
        var offsetMatrix = new Matrix4D();
        configureMatrix(offsetMatrix);
        offsetMatrix.transformVector(offset);
        getCenter().copy(new Dot4D(getCenter(), offset));
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
        matrix[0][1] = vx.y;
        matrix[0][2] = vx.z;
        matrix[1][0] = vy.x;
        matrix[1][1] = vy.y;
        matrix[1][2] = vy.z;
        matrix[2][0] = vz.x;
        matrix[2][1] = vz.y;
        matrix[2][2] = vz.z;
    }
}
