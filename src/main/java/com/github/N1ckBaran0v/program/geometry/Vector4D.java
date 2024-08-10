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

    public Vector4D(Vector4D start, Vector4D end) {
        this.x = end.x - start.x;
        this.y = end.y - start.y;
        this.z = end.z - start.z;
    }

    public void add(Vector4D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public Vector4D(Vector4D a, Vector4D b, Vector4D c) {
        this(new Vector4D(a, b), new Vector4D(b, c));
    }

    public Vector4D(Vector4D a, Vector4D b) {
        this(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
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

    public double multiply(Vector4D other) {
        return x * other.x + y * other.y + z * other.z;
    }
}
