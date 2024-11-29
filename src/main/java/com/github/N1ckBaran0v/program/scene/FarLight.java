package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.math.Vector3D;
import org.jetbrains.annotations.NotNull;

public class FarLight extends SceneObject {
    private final Vector3D direction = new Vector3D(0, -1, 0);

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
    public void accept(@NotNull SceneObjectVisitor visitor) {
        visitor.visit(this);
    }

    public Vector3D getDirection() {
        return direction;
    }
}
