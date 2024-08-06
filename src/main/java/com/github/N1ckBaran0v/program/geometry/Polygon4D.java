package com.github.N1ckBaran0v.program.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon4D implements Iterable<Dot4D> {
    public final List<Dot4D> dots = new ArrayList<>();
    public final Vector4D normal;
    public final Color color;

    public Polygon4D(Dot4D first, Dot4D second, Dot4D third, Vector4D normal, Color color) {
        this.color = color;
        dots.add(first);
        dots.add(second);
        dots.add(third);
        this.normal = normal;
    }

    @Override
    public Iterator<Dot4D> iterator() {
        return dots.iterator();
    }
}
