package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Polygon;
import com.github.N1ckBaran0v.program.geometry.Vector3D;

abstract public class PolygonalModel extends SceneObject implements Iterable<Polygon> {
    protected PolygonalModel() {
    }

    protected PolygonalModel(Vector3D center) {
        super(center);
    }

    @Override
    public void accept(SceneObjectVisitor visitor) {
        visitor.visit(this);
    }
}
