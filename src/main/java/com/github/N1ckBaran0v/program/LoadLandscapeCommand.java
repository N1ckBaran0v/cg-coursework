package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.LandscapeHolder;

public class LoadLandscapeCommand implements Command {
    private final String path;

    public LoadLandscapeCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(Context context) {
        LandscapeHolder.load(path);
    }
}
