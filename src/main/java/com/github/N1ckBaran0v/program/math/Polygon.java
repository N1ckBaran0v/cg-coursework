package com.github.N1ckBaran0v.program.math;

import com.github.N1ckBaran0v.program.render.Color;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon implements Iterable<PolygonVector> {
    public final List<PolygonVector> dots = new ArrayList<>();
    public final Vector3D normal;
    public final Color color;

    public Polygon(@NotNull PolygonVector first, @NotNull PolygonVector second, @NotNull PolygonVector third,
                   @NotNull Vector3D normal, @NotNull Color color) {
        this.color = color;
        this.normal = normal;
        dots.add(first);
        dots.add(second);
        dots.add(third);
    }

    @Override
    public @NotNull Iterator<PolygonVector> iterator() {
        return dots.iterator();
    }
}
