package com.github.N1ckBaran0v.program.geometry;

public class Matrix4D {
    private final double[][] matrix;

    public Matrix4D() {
        matrix = createMatrix();
    }

    private Matrix4D(double[][] matrix) {
        this.matrix = matrix;
    }

    public static Matrix4D getOffsetMatrix(double dx, double dy, double dz) {
        var result = new Matrix4D();
        var matrix = result.matrix;
        matrix[0][3] = dx;
        matrix[1][3] = dy;
        matrix[2][3] = dz;
        return result;
    }

    public static Matrix4D getRotateMatrix(double ax, double ay, double az) {
        var m1 = new Matrix4D();
        var b1 = m1.matrix;
        var rx = Math.toRadians(ax);
        b1[1][1] = b1[2][2] = Math.cos(rx);
        b1[1][2] = Math.sin(rx);
        b1[2][1] = -b1[1][2];
        var m2 = new Matrix4D();
        var b2 = m2.matrix;
        var ry = Math.toRadians(ax);
        b2[2][2] = b2[0][0] = Math.cos(ry);
        b2[2][0] = Math.sin(ry);
        b2[0][2] = -b2[2][0];
        var m3 = new Matrix4D();
        var b3 = m3.matrix;
        var rz = Math.toRadians(ax);
        b3[0][0] = b3[1][1] = Math.cos(rz);
        b3[0][1] = Math.sin(rz);
        b3[1][0] = -b3[0][1];
        return m1.multiply(m2).multiply(m3);
    }

    public static Matrix4D getScaleMatrix(double cx, double cy, double cz) {
        var result = new Matrix4D();
        var matrix = result.matrix;
        matrix[0][0] = cx;
        matrix[1][1] = cy;
        matrix[2][2] = cz;
        return result;
    }

    public void transformVector(Vector4D vector) {
        var x = vector.x * matrix[0][0] + vector.y * matrix[0][1] + vector.z * matrix[0][2] + vector.w * matrix[0][3];
        var y = vector.x * matrix[1][0] + vector.y * matrix[1][1] + vector.z * matrix[1][2] + vector.w * matrix[1][3];
        var z = vector.x * matrix[2][0] + vector.y * matrix[2][1] + vector.z * matrix[2][2] + vector.w * matrix[2][3];
        var w = vector.x * matrix[3][0] + vector.y * matrix[3][1] + vector.z * matrix[3][2] + vector.w * matrix[3][3];
        vector.x = x;
        vector.y = y;
        vector.z = z;
        vector.w = w;
    }

    private Matrix4D multiply(Matrix4D other) {
        var temp = createMatrix();
        for (var i = 0; i < 3; ++i) {
            temp[i][i] = 0;
            for (var j = 0; j < 3; ++j) {
                for (var k = 0; k < 3; ++k) {
                    temp[i][j] += matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return new Matrix4D(temp);
    }

    private double[][] createMatrix() {
        var matrix = new double[4][];
        for (var i = 0; i < 4; ++i) {
            matrix[i] = new double[4];
            matrix[i][i] = 1;
        }
        return matrix;
    }
}
