package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Dot4D;
import com.github.N1ckBaran0v.program.geometry.Polygon4D;

import java.util.*;

class SphereBuilder {
    private final List<Dot4D> dots = new ArrayList<>();

    public SphereBuilder() {
        var radius = 300.0;
        dots.add(new Dot4D(0, 0, radius));
        dots.add(new Dot4D(radius, 0, 0));
        dots.add(new Dot4D(0, radius, 0));
        dots.add(new Dot4D(-radius, 0, 0));
        dots.add(new Dot4D(0, -radius, 0));
        dots.add(new Dot4D(0, 0, -radius));
    }

    public Dot4D createCenter() {
        return new Dot4D();
    }

    public List<Polygon4D> createPolygons() {
        var polygons = new ArrayList<Polygon4D>();
        var gen = new Random();
        var dh = dots.get(0);
        var sqr = new Dot4D[4];
        for (var i = 0; i < 4; ++i) {
            sqr[i] = dots.get(i + 1);
        }
        var dl = dots.get(5);
        for (var i = 0; i < 4; ++i) {
            polygons.add(new Polygon4D(dh, sqr[i], sqr[(i + 1) % 4], getColor(gen)));
            polygons.add(new Polygon4D(dl, sqr[i], sqr[(i + 1) % 4], getColor(gen)));
        }
        return polygons;
    }

    public Set<Dot4D> createDots() {
        return new HashSet<>(dots);
    }

    private Color getColor(Random gen) {
        return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
    }
}
