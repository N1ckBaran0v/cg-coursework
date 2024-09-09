package com.github.N1ckBaran0v.program.geometry;

public class Vector3D {
    public double x, y, z;

    public Vector3D() {
    }

    public Vector3D(double x, double y, double z) {
        set(x, y, z);
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void add(Vector3D dot) {
        x += dot.x;
        y += dot.y;
        z += dot.z;
    }

    public static Vector3D getNormal(PolygonVector a, PolygonVector b, PolygonVector c) {
        var result = vectorMultiply(sub(a.realDot, b.realDot), sub(b.realDot, c.realDot));
        result.normalize();
        return result;
    }

    public static Vector3D sub(Vector3D start, Vector3D end) {
        return new Vector3D(end.x - start.x, end.y - start.y, end.z - start.z);
    }

    public static Vector3D vectorMultiply(Vector3D a, Vector3D b) {
        return new Vector3D(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
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

    public double scalarMultiply(Vector3D other) {
        return x * other.x + y * other.y + z * other.z;
    }
}
