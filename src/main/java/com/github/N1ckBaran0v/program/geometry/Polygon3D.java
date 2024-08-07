package com.github.N1ckBaran0v.program.geometry;

public class Polygon3D {
    public final Dot3D first, second, third;
    public final Color color;

    public Polygon3D(Dot3D first, Dot3D second, Dot3D third, Color color) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.color = color;
    }
}
