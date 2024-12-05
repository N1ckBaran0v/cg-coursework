package com.github.N1ckBaran0v.program.math;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vector3D {
    public double x, y, z;
    private static final double EPS = 5e-2;

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

    public void add(@NotNull Vector3D dot) {
        x += dot.x;
        y += dot.y;
        z += dot.z;
    }

    public static Vector3D getNormal(@NotNull PolygonVector a, @NotNull PolygonVector b, @NotNull PolygonVector c) {
        var result = vectorMultiply(sub(a.realDot, b.realDot), sub(b.realDot, c.realDot));
        result.normalize();
        return result;
    }

    public static Vector3D sub(@NotNull Vector3D start, @NotNull Vector3D end) {
        return new Vector3D(end.x - start.x, end.y - start.y, end.z - start.z);
    }

    public static Vector3D vectorMultiply(@NotNull Vector3D a, @NotNull Vector3D b) {
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

    public double scalarMultiply(@NotNull Vector3D other) {
        return x * other.x + y * other.y + z * other.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3D vector3D)) return false;
        return Math.abs(x - vector3D.x) < EPS && Math.abs(y - vector3D.y) < EPS && Math.abs(z - vector3D.z) < EPS;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
