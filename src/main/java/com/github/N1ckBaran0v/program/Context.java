package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.artist.Artist;
import com.github.N1ckBaran0v.program.scene.Scene;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class Context {
    private final Scene scene;
    private final Artist artist;

    @Inject
    public Context(Scene scene, Artist artist) {
        this.scene = scene;
        this.artist = artist;
    }

    public Scene getScene() {
        return scene;
    }

    public Artist getArtist() {
        return artist;
    }
}
