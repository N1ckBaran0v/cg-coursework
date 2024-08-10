package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Polygon4D;

abstract public class PolygonalModel extends SceneObject implements Iterable<Polygon4D> {
    protected PolygonalModel() {
    }

    protected PolygonalModel(Dot4D center) {
        super(center);
    }

    @Override
    public void accept(SceneObjectVisitor visitor) {
        visitor.visit(this);
    }
}
