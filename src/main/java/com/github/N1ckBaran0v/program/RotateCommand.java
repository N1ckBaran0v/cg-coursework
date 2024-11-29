package com.github.N1ckBaran0v.program;

import org.jetbrains.annotations.NotNull;

public class RotateCommand implements Command {
    private final double ax, ay, az;
    private final String name;

    public RotateCommand(String name, double ax, double ay, double az) {
        this.ax = ax;
        this.ay = ay;
        this.az = az;
        this.name = name;
    }

    @Override
    public void execute(@NotNull Context context) {
        var scene = context.getScene();
        var object = scene.getObject(name);
        object.rotate(ax, ay, az);
    }
}
