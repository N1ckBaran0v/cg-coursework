package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Polygon4D;

abstract public class PolygonalModel extends SceneObject implements Iterable<Polygon4D> {
    @Override
    public void accept(SceneObjectVisitor visitor) {
        visitor.visit(this);
    }
}
