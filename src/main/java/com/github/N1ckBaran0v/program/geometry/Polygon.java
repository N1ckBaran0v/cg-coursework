package com.github.N1ckBaran0v.program.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon implements Iterable<PolygonVector> {
    public final List<PolygonVector> dots = new ArrayList<>();
    public final Vector3D normal;
    public final Color color;

    public Polygon(PolygonVector first, PolygonVector second, PolygonVector third, Vector3D normal, Color color) {
        this.color = color;
        this.normal = normal;
        dots.add(first);
        dots.add(second);
        dots.add(third);
    }

    @Override
    public Iterator<PolygonVector> iterator() {
        return dots.iterator();
    }
}
