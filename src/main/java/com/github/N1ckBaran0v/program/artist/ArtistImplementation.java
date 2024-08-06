package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.scene.Scene;

import javax.inject.Inject;

public class ArtistImplementation implements Artist {
    private final Scene scene;
    private final AbstractDrawFactory abstractDrawFactory;

    @Inject
    public ArtistImplementation(Scene scene, AbstractDrawFactory abstractDrawFactory) {
        this.scene = scene;
        this.abstractDrawFactory = abstractDrawFactory;
    }

    @Override
    public void draw() {

    }
}
