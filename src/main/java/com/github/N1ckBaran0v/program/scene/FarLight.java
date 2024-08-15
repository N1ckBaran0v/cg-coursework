package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.geometry.Vector4D;

public class FarLight extends SceneObject {
    private final Vector4D direction = new Vector4D(0, -1, 0);

    @Override
    public void rotate(double ax, double ay, double az) {
        var rx = Math.toRadians(ax);
        var rz = Math.toRadians(az);
        var x = Math.sin(rx);
        var y = -Math.cos(rz) * Math.cos(rx);
        var z = Math.sin(rz) * Math.cos(rx);
        direction.set(x, y, z);
        setNeedRecalculate(true);
    }

    @Override
    public void accept(SceneObjectVisitor visitor) {
        visitor.visit(this);
    }

    public Vector4D getDirection() {
        return direction;
    }
}
