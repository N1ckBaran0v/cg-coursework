package com.github.N1ckBaran0v.program.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon3D implements Iterable<Dot3D> {
    public final List<Dot3D> dots = new ArrayList<>();
    public final Color color;

    public Polygon3D(Dot3D first, Dot3D second, Dot3D third, Color color) {
        this.color = color;
        dots.add(first);
        dots.add(second);
        dots.add(third);
    }

    @Override
    public Iterator<Dot3D> iterator() {
        return dots.iterator();
    }
}
