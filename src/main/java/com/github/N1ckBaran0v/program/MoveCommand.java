package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.geometry.Matrix4D;

public class MoveCommand extends Command {
    private final double dx, dy, dz;
    private final String name;

    public MoveCommand(String name, double dx, double dy, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.name = name;
    }

    @Override
    void execute() {
        var scene = context.getScene();
        var object = scene.getObject(name);
        var matrix = Matrix4D.getOffsetMatrix(dx, dy, dz);
        object.transform(matrix);
    }
}
