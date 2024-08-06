package com.github.N1ckBaran0v.program.geometry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon4D implements Iterable<Dot4D> {
    private List<Dot4D> dots = new ArrayList<>();

    public Polygon4D(Dot4D first, Dot4D second, Dot4D third) {
        dots.add(first);
        dots.add(second);
        dots.add(third);
    }

    @Override
    public Iterator<Dot4D> iterator() {
        return dots.iterator();
    }
}
