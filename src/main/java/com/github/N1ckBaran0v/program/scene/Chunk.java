package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Polygon4D;

import java.util.Iterator;
import java.util.List;

class Chunk extends PolygonalModel {
    private final List<Polygon4D> polygons;

    public Chunk(List<Polygon4D> polygons) {
        this.polygons = polygons;
    }

    @Override
    public Iterator<Polygon4D> iterator() {
        return polygons.iterator();
    }
}
