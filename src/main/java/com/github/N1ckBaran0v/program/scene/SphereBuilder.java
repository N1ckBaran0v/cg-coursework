package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Dot4D;
import com.github.N1ckBaran0v.program.geometry.Polygon4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

import java.util.*;

class SphereBuilder {
    private final List<List<Dot4D>> dots = new ArrayList<>();

    public SphereBuilder() {
        var radius = 300.0;
        var step = 15;
        for (var i = 0; i < 360; i += step) {
            var curr = new ArrayList<Dot4D>();
            if (i == 0) {
                for (var angle = 0; angle < 360; angle += step) {
                    var rads = Math.toRadians(angle);
                    curr.add(new Dot4D(Math.sin(rads) * radius, 0, Math.cos(rads) * radius));
                }
            } else {
                var prev = dots.get(dots.size() - 1);
                var y = Math.sin(Math.toRadians(i));
                for (var j = 0; j < prev.size(); ++j) {
                    var dot = prev.get(j);
                    if (j == 0 || ((prev.size() & 1) == 0 && j == (prev.size() >> 1))) {
                        curr.add(dot);
                    } else {
                        curr.add(new Dot4D(dot.x, y, dot.z));
                    }
                }
            }
            dots.add(curr);
        }
    }

    public Dot4D createCenter() {
        return new Dot4D();
    }

    public List<Polygon4D> createPolygons() {
        var polygons = new ArrayList<Polygon4D>();
        var gen = new Random();
        for (var i = 0; i < dots.size(); ++i) {
            var curr = dots.get(i);
            var next = dots.get((i + 1) % dots.size());
            for (var j = 0; j < curr.size(); ++j) {
                var d1 = curr.get(j);
                var d2 = next.get(j);
                var d3 = curr.get((i + 1) % curr.size());
                var d4 = next.get((i + 1) % next.size());
                if (d1 != d2) {
                    var vec = new Vector4D(d1, d2, d3);
                    if (vec.multiply(new Vector4D(d1)) < 0) {
                        vec.inverse();
                    }
                    polygons.add(new Polygon4D(d1, d2, d3, vec, getColor(gen)));
                }
                if (d3 != d3) {
                    var vec = new Vector4D(d2, d3, d4);
                    if (vec.multiply(new Vector4D(d1)) < 0) {
                        vec.inverse();
                    }
                    polygons.add(new Polygon4D(d2, d3, d4, vec, getColor(gen)));
                }
            }
        }
        return polygons;
    }

    public Set<Dot4D> createDots() {
        var result = new HashSet<Dot4D>();
        for (var elem : dots) {
            for (var dot : elem) {
                result.add(dot);
            }
        }
        return result;
    }

    private Color getColor(Random gen) {
        return new Color(gen.nextInt(256), gen.nextInt(256), gen.nextInt(256));
    }
}
