package com.github.N1ckBaran0v.program.geometry;

public class Dot4D {
    public double x;
    public double y;
    public double z;
    public double w;

    public Dot4D() {
        x = y = z = 0;
        w = 1;
    }

    public Dot4D(double x, double y, double z) {
        this(x, y, z, 1);
    }

    public Dot4D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Dot4D(Dot4D start, Vector4D offset) {
        this.x = start.x + offset.x;
        this.y = start.y + offset.y;
        this.z = start.z + offset.z;
        this.w = 1;
    }

    public Dot4D(Vector4D offset) {
        this.x = offset.x;
        this.y = offset.y;
        this.z = offset.z;
        this.w = offset.w;
    }

    public Dot3D getDot3D() {
        return new Dot3D(x / w, y / w, z / w);
    }

    public void copy(Dot4D other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.w = other.w;
    }
}
