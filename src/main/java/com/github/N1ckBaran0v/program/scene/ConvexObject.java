package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Polygon4D;
import com.github.N1ckBaran0v.program.geometry.Vector4D;

import java.util.*;

public class ConvexObject extends PolygonalModel {
    private final List<Polygon4D> polygons;
    private final Set<Dot4D> dots;

    public ConvexObject(Dot4D center, List<Polygon4D> polygons, Set<Dot4D> dots) {
        super(center);
        this.polygons = polygons;
        this.dots = dots;
    }

    @Override
    public void move(double dx, double dy, double dz) {
        var offset = new Vector4D(dx, dy, dz);
        for (var dot : dots) {
            dot.copy(new Dot4D(dot, offset));
        }
        super.move(dx, dy, dz);
    }

//    @Override
//    public void rotate(double ax, double ay, double az) {
//        var tm = Matrix4D.getRotateMatrix(ax, ay, az);
//        var center = getCenter();
//        for (var dot : dots) {
//            var offset = new Vector4D(center, dot);
//            tm.transformVector(offset);
//            dot.copy(new Dot4D(center, offset));
//        }
//    }

    @Override
    public Iterator<Polygon4D> iterator() {
        return polygons.iterator();
    }
}
