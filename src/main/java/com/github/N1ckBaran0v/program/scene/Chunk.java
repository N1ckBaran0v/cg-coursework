package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Polygon;

import java.util.Iterator;
import java.util.List;

class Chunk extends PolygonalModel {
    private final List<Polygon> polygons;

    public Chunk(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    @Override
    public Iterator<Polygon> iterator() {
        return polygons.iterator();
    }
}
