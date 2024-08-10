package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Polygon4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

import java.util.*;

class ConvexModelBuilder {
    private final List<Vector4D> dots = new ArrayList<>();

    private void innerCreateDots() {
        dots.clear();
        var radius = 300.0;
        dots.add(new Vector4D(0, 0, radius));
        dots.add(new Vector4D(radius, 0, 0));
        dots.add(new Vector4D(0, radius, 0));
        dots.add(new Vector4D(-radius, 0, 0));
        dots.add(new Vector4D(0, -radius, 0));
        dots.add(new Vector4D(0, 0, -radius));
    }

    public Vector4D createCenter() {
        return new Vector4D();
    }

    public List<Polygon4D> createPolygons() {
        innerCreateDots();
        var polygons = new ArrayList<Polygon4D>();
        var gen = new Random();
        var dh = dots.get(0);
        var sqr = new Vector4D[4];
        for (var i = 0; i < 4; ++i) {
            sqr[i] = dots.get(i + 1);
        }
        var dl = dots.get(5);
        for (var i = 0; i < 4; ++i) {
            polygons.add(new Polygon4D(dh, sqr[i], sqr[(i + 1) % 4], new Vector4D(), getColor(gen)));
            polygons.add(new Polygon4D(dl, sqr[i], sqr[(i + 1) % 4], new Vector4D(), getColor(gen)));
        }
        return polygons;
    }

    public Set<Vector4D> createDots() {
        return new HashSet<>(dots);
    }

    private Color getColor(Random gen) {
        return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
    }
}
