package com.github.N1ckBaran0v.program.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon4D implements Iterable<Vector4D> {
    public final List<Vector4D> dots = new ArrayList<>();
    public final Vector4D normal;
    public final Color color;

    public Polygon4D(Vector4D first, Vector4D second, Vector4D third, Vector4D normal, Color color) {
        this.color = color;
        this.normal = normal;
        dots.add(first);
        dots.add(second);
        dots.add(third);
    }

    @Override
    public Iterator<Vector4D> iterator() {
        return dots.iterator();
    }
}
