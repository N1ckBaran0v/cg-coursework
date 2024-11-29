package com.github.N1ckBaran0v.program.math;

public class PolygonVector {
    public final Vector3D realDot;
    public final DrawVector drawDot = new DrawVector();
    public final Vector3D normal = new Vector3D();

    public PolygonVector(double x, double y, double z) {
        this.realDot = new Vector3D(x, y, z);
    }
}
