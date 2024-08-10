package com.github.N1ckBaran0v.program.geometry;

public class Vector4D {
    public double x;
    public double y;
    public double z;
    public double w = 1;


    public Vector4D() {
    }

    public Vector4D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector4D(Vector4D other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.w = other.w;
    }

    public static Vector4D sub(Vector4D start, Vector4D end) {
        return new Vector4D(end.x - start.x, end.y - start.y, end.z - start.z);
    }

    public void add(Vector4D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public void add(double dx, double dy, double dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }

    public static Vector4D getNormal(Vector4D a, Vector4D b, Vector4D c) {
        var result = vectorMultiply(Vector4D.sub(a, b), Vector4D.sub(b, c));
        result.normalize();
        return result;
    }

    public static Vector4D vectorMultiply(Vector4D a, Vector4D b) {
        return new Vector4D(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    public void normalize() {
        var len = length();
        x /= len;
        y /= len;
        z /= len;
    }

    public void inverse() {
        x = -x;
        y = -y;
        z = -z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double scalarMultiply(Vector4D other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector3D toVector3D(double cx, double cy) {
        return new Vector3D(cx * (x / w + 1), cy * (y / w + 1), z / w - 1);
    }
}
