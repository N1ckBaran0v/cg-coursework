package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.math.Polygon;
import com.github.N1ckBaran0v.program.math.Vector3D;
import org.jetbrains.annotations.NotNull;

abstract public class PolygonalModel extends SceneObject implements Iterable<Polygon> {
    protected PolygonalModel() {
    }

    protected PolygonalModel(@NotNull Vector3D center) {
        super(center);
    }

    @Override
    public void accept(@NotNull SceneObjectVisitor visitor) {
        visitor.visit(this);
    }
}
