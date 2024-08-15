package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.LandscapeHolder;

public class SaveLandscapeCommand implements Command {
    private final String path;

    public SaveLandscapeCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(Context context) {
        LandscapeHolder.save(path);
    }
}
