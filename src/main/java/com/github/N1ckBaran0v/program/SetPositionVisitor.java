package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.Composite;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

class SetPositionVisitor implements SceneObjectVisitor {
    private final double x, y, z;
    private final long visibility;

    public SetPositionVisitor(double x, double y, double z, long visibility) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.visibility = visibility;
    }

    @Override
    public void visit(Camera camera) {
        camera.setPosition(x, y, z);
        camera.setVisibility(visibility);
    }

    @Override
    public void visit(Composite composite) {
    }
}
