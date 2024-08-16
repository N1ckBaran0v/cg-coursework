package com.github.N1ckBaran0v.program;

public class AddObjectCommand implements Command {
    private final String name;
    private final String type;

    public AddObjectCommand(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void execute(Context context) {
        var scene = context.getScene();
        var object = scene.createObject(type);
        scene.addObject(name, object);
    }
}
