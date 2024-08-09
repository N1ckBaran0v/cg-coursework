package com.github.N1ckBaran0v.program;

public class RotateCommand extends Command {
    private final double ax, ay, az;
    private final String name;

    public RotateCommand(String name, double ax, double ay, double az) {
        this.ax = ax;
        this.ay = ay;
        this.az = az;
        this.name = name;
    }

    @Override
    void execute() {
        var scene = context.getScene();
        var object = scene.getObject(name);
        object.rotate(ax, ay, az);
    }
}
