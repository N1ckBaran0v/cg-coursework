package com.github.N1ckBaran0v.program.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon3D implements Iterable<Dot3D> {
    private List<Dot3D> dots = new ArrayList<>();

    public void addDot(Dot3D dot) {
        dots.add(dot);
    }

    @Override
    public Iterator<Dot3D> iterator() {
        return dots.iterator();
    }
}
