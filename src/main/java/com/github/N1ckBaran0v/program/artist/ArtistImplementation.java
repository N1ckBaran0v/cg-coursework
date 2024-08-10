package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.Scene;

import javax.inject.Inject;

public class ArtistImplementation implements Artist {
    private final Scene scene;
    private final AbstractDrawFactory abstractDrawFactory;
    private final DrawStrategyCreator drawStrategyCreator;

    @Inject
    public ArtistImplementation(Scene scene, AbstractDrawFactory abstractDrawFactory, DrawStrategyCreator drawStrategyCreator) {
        this.scene = scene;
        this.abstractDrawFactory = abstractDrawFactory;
        this.drawStrategyCreator = drawStrategyCreator;
    }

    @Override
    public void draw(String cameraName) {
        var camera = (Camera) scene.getObject(cameraName);
        var image = abstractDrawFactory.getImage(cameraName);
        var visitor = new TransformVisitor(camera, drawStrategyCreator, image);
        for (var object : scene) {
            object.accept(visitor);
        }
        var graphics = abstractDrawFactory.getGraphics(cameraName);
        graphics.draw(image);
    }
}
