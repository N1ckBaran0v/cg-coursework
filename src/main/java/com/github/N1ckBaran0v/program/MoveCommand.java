package com.github.N1ckBaran0v.program;

import org.jetbrains.annotations.NotNull;

public class MoveCommand implements Command {
    private final double dx, dy, dz;
    private final String name;

    public MoveCommand(String name, double dx, double dy, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.name = name;
    }

    @Override
    public void execute(@NotNull Context context) {
        var scene = context.getScene();
        var object = scene.getObject(name);
        object.move(dx, dy, dz);
    }
}
