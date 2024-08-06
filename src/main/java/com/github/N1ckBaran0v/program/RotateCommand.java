package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.geometry.Matrix4D;

public class RotateCommand extends Command {
    private final double cx, cy, cz;
    private final String name;

    public RotateCommand(double cx, double cy, double cz, String name) {
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
        this.name = name;
    }

    @Override
    void execute() {
        var scene = context.getScene();
        var object = scene.getObject(name);
        var matrix = Matrix4D.getRotateMatrix(cx, cy, cz);
        object.transform(matrix);
    }
}
