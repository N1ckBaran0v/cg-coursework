package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Vector4D;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.Scene;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RenderImplementation implements Render {
    private final Scene scene;
    private final AbstractDrawFactory abstractDrawFactory;
    private final DrawStrategyCreator drawStrategyCreator;
    private final Color background = new Color(0, 255, 255, 1.0);
    private Map<Vector4D, Double> brightnesses = new HashMap<>();

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
            var normalVisitor = new CalculateNormalsVisitor();
            for (var object : scene) {
                object.accept(normalVisitor);
            }
            var lightCalculator = new CalculateLight(lightChecker.getLights());
            brightnesses = new HashMap<>();
            var normals = normalVisitor.getNormals();
            for (var dot : normals.keySet()) {
                brightnesses.put(dot, lightCalculator.calculateBrightness(normals.get(dot)));
            }
            background.setBrightness(lightCalculator.calculateBrightness(new Vector4D(0, 1, 0)));
        }
        var imageRenderer = new TransformVisitor(camera, drawStrategyCreator, image, background, brightnesses);
        for (var object : scene) {
            object.accept(imageRenderer);
        }
        imageRenderer.removeCreated();
        var graphics = abstractDrawFactory.getGraphics(cameraName);
        graphics.draw(image);
    }
}
