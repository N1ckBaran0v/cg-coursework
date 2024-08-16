package com.github.N1ckBaran0v.program.geometry;

public class Matrix4D {
    public double xx, xy, xz, xw;
    public double yx, yy, yz, yw;
    public double zx, zy, zz, zw;
    public double wx, wy, wz, ww;

    public Matrix4D() {
        xx = yy = zz = ww = 1;
    }

    public void transformVector(Vector4D vector) {
        var x = vector.x * xx + vector.y * xy + vector.z * xz + vector.w * xw;
        var y = vector.x * yx + vector.y * yy + vector.z * yz + vector.w * yw;
        var z = vector.x * zx + vector.y * zy + vector.z * zz + vector.w * zw;
        var w = vector.x * wx + vector.y * wy + vector.z * wz + vector.w * ww;
        vector.x = x;
        vector.y = y;
        vector.z = z;
        vector.w = w;
    }
}
