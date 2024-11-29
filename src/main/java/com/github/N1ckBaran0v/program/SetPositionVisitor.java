package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;
import org.jetbrains.annotations.NotNull;

class SetPositionVisitor implements SceneObjectVisitor {
    private final double x, y, z;

    public SetPositionVisitor(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void visit(@NotNull Camera camera) {
        camera.setPosition(x, y, z);
    }
}
