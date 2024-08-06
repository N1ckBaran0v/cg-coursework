package com.github.N1ckBaran0v.program;

public class AddObjectCommand extends Command {
    private final String name;
    private final String type;

    public AddObjectCommand(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    void execute() {
        var scene = context.getScene();
        var object = scene.createObject(type);
        scene.addObject(name, object);
    }
}
