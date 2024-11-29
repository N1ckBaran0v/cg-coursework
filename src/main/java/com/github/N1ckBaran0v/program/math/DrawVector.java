package com.github.N1ckBaran0v.program.math;

import org.jetbrains.annotations.NotNull;

public class DrawVector {
    public double x, y, z, w;
    public boolean isUsed, isVisible;
    public double brightness;

    public void set(@NotNull Vector3D start, @NotNull Vector3D end) {
        x = end.x - start.x;
        y = end.y - start.y;
        z = end.z - start.z;
        w = 1;
    }

    public void to3D() {
        x /= w;
        y /= w;
        z /= w;
    }
}
