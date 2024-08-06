package com.github.N1ckBaran0v.program.geometry;

public class Vector4D {
    public double x;
    public double y;
    public double z;
    public double w;

    public Vector4D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public Vector4D(Dot4D start, Dot4D end) {
        this.x = end.x - start.x;
        this.y = end.y - start.y;
        this.z = end.z - start.z;
        this.w = 1;
    }
}
