package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.render.Render;
import com.github.N1ckBaran0v.program.scene.Scene;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Context {
    private final Scene scene;
    private final Render render;

    @Inject
    public Context(Scene scene, Render render) {
        this.scene = scene;
        this.render = render;
    }

    public Scene getScene() {
        return scene;
    }

    public Render getArtist() {
        return render;
    }
}
