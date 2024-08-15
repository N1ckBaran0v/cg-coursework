package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.Scene;

import javax.inject.Inject;

public class RenderImplementation implements Render {
    private final Scene scene;
    private final AbstractDrawFactory abstractDrawFactory;
    private final DrawStrategyCreator drawStrategyCreator;

    @Inject
    public RenderImplementation(Scene scene, AbstractDrawFactory abstractDrawFactory, DrawStrategyCreator drawStrategyCreator) {
        this.scene = scene;
        this.abstractDrawFactory = abstractDrawFactory;
        this.drawStrategyCreator = drawStrategyCreator;
    }

    @Override
    public void draw(String cameraName) {
        var camera = (Camera) scene.getObject(cameraName);
        var image = abstractDrawFactory.getImage(cameraName);
        var lightChecker = new CollectLightsVisitor();
        var needRecalculate = false;
        for (var object : scene) {
            object.accept(lightChecker);
            if (object.isNeedRecalculate()) {
                object.setNeedRecalculate(false);
                needRecalculate = true;
            }
        }
        if (needRecalculate) {
            var lightCalculator = new CalculateLightVisitor(lightChecker.getLights());
            for (var object : scene) {
                object.accept(lightCalculator);
            }
        }
        var imageRenderer = new TransformVisitor(camera, drawStrategyCreator, image);
        for (var object : scene) {
            object.accept(imageRenderer);
        }
        var graphics = abstractDrawFactory.getGraphics(cameraName);
        graphics.draw(image);
    }
}
