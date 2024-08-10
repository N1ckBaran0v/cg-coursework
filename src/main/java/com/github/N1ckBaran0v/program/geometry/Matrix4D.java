package com.github.N1ckBaran0v.program.geometry;

public class Matrix4D {
    public double xx, xy, xz, xw;
    public double yx, yy, yz, yw;
    public double zx, zy, zz, zw;
    public double wx, wy, wz, ww;

    public Matrix4D() {
        xx = yy = zz = ww = 1;
    }

    public Matrix4D(Matrix4D other) {
        this.xx = other.xx;
        this.xy = other.xy;
        this.xz = other.xz;
        this.xw = other.xw;
        this.yx = other.yx;
        this.yy = other.yy;
        this.yz = other.yz;
        this.yw = other.yw;
        this.zx = other.zx;
        this.zy = other.zy;
        this.zz = other.zz;
        this.zw = other.zw;
        this.wx = other.wx;
        this.wy = other.wy;
        this.wz = other.wz;
        this.ww = other.ww;
    }

    public static Matrix4D getOffsetMatrix(double dx, double dy, double dz) {
        var matrix = new Matrix4D();
        matrix.xw = dx;
        matrix.yw = dy;
        matrix.zw = dz;
        return matrix;
    }

    public static Matrix4D getRotateMatrix(double angle, Axis axis) {
        var result = new Matrix4D();
        var radians = Math.toRadians(angle);
        switch(axis) {
            case OX:
                result.yy = result.zz = Math.cos(radians);
                result.yz = Math.sin(radians);
                result.zy = -result.yz;
                break;
            case OY:
                result.zz = result.xx = Math.cos(radians);
                result.zx = Math.sin(radians);
                result.xz = -result.zx;
                break;
            case OZ:
                result.xx = result.yy = Math.cos(radians);
                result.xy = Math.sin(radians);
                result.yx = -result.xy;
                break;
        }
        return result;
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

    public Matrix4D multiply(Matrix4D other) {
        var result = new Matrix4D();
        result.xx = xx * other.xx + xy * other.yx + xz * other.zx + xw * other.wx;
        result.xy = xx * other.xy + xy * other.yy + xz * other.zy + xw * other.wy;
        result.xz = xx * other.xz + xy * other.yz + xz * other.zz + xw * other.wz;
        result.xw = xx * other.xw + xy * other.yw + xz * other.zw + xw * other.ww;
        result.yx = yx * other.xx + yy * other.yx + yz * other.zx + yw * other.wx;
        result.yy = yx * other.xy + yy * other.yy + yz * other.zy + yw * other.wy;
        result.yz = yx * other.xz + yy * other.yz + yz * other.zz + yw * other.wz;
        result.yw = yx * other.xw + yy * other.yw + yz * other.zw + yw * other.ww;
        result.zx = zx * other.xx + zy * other.yx + zz * other.zx + zw * other.wx;
        result.zy = zx * other.xy + zy * other.yy + zz * other.zy + zw * other.wy;
        result.zz = zx * other.xz + zy * other.yz + zz * other.zz + zw * other.wz;
        result.zw = zx * other.xw + zy * other.yw + zz * other.zw + zw * other.ww;
        result.wx = wx * other.xx + wy * other.yx + wz * other.zx + ww * other.wx;
        result.wy = wx * other.xy + wy * other.yy + wz * other.zy + ww * other.wy;
        result.wz = wx * other.xz + wy * other.yz + wz * other.zz + ww * other.wz;
        result.ww = wx * other.xw + wy * other.yw + wz * other.zw + ww * other.ww;
        return result;
    }

    @Override
    public String toString() {
        return "Matrix4D{" +
                "xx=" + xx +
                ", xy=" + xy +
                ", xz=" + xz +
                ", xw=" + xw +
                ",\n         yx=" + yx +
                ", yy=" + yy +
                ", yz=" + yz +
                ", yw=" + yw +
                ",\n         zx=" + zx +
                ", zy=" + zy +
                ", zz=" + zz +
                ", zw=" + zw +
                ",\n         wx=" + wx +
                ", wy=" + wy +
                ", wz=" + wz +
                ", ww=" + ww +
                '}';
    }
}
