package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.math.PolygonVector;
import com.github.N1ckBaran0v.program.math.Vector3D;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.scene.Camera;
import com.github.N1ckBaran0v.program.scene.Scene;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.Set;

public class RenderImplementation implements Render {
    private final Scene scene;
    private final AbstractDrawFactory abstractDrawFactory;
    private final DrawStrategyCreator drawStrategyCreator;
    private final Color background = new Color(0, 255, 255, 1.0);

    @Inject
    public RenderImplementation(@NotNull Scene scene, @NotNull AbstractDrawFactory abstractDrawFactory,
                                @NotNull DrawStrategyCreator drawStrategyCreator) {
        this.scene = scene;
        this.abstractDrawFactory = abstractDrawFactory;
        this.drawStrategyCreator = drawStrategyCreator;
    }

    @Override
    public void draw(@NotNull String cameraName) {
        var camera = (Camera) scene.getObject(cameraName);
        var image = abstractDrawFactory.getImage(cameraName);
        var needRecalculate = false;
        var collector = new CollectorVisitor();
        for (var object : scene) {
            object.accept(collector);
            if (object.isNeedRecalculate()) {
                object.setNeedRecalculate(false);
                needRecalculate = true;
            }
        }
        var dots = collector.getDots();
        if (needRecalculate) {
            for (var dot : dots) {
                dot.normal.set(0, 0, 0);
            }
            var normalVisitor = new CalculateNormalsVisitor();
            for (var object : scene) {
                object.accept(normalVisitor);
            }
            var lightCalculator = new CalculateLight(collector.getLights());
            for (var dot : dots) {
                dot.drawDot.brightness = lightCalculator.calculateBrightness(dot.normal);
            }
            background.setBrightness(lightCalculator.calculateBrightness(new Vector3D(0, 1, 0)));
        }
        reset(dots);
        var imageRenderer = new TransformVisitor(camera, drawStrategyCreator, image, background);
        for (var object : scene) {
            object.accept(imageRenderer);
        }
        var graphics = abstractDrawFactory.getGraphics(cameraName);
        graphics.draw(image);
    }

    private void reset(@NotNull Set<PolygonVector> dots) {
        for (var dot : dots) {
            dot.drawDot.isUsed = false;
        }
    }
}
