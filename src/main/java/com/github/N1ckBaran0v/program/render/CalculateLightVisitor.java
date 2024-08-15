package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.scene.FarLight;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

import java.util.List;

class CalculateLightVisitor implements SceneObjectVisitor {
    private final List<FarLight> lights;

    public CalculateLightVisitor(List<FarLight> lights) {
        this.lights = lights;
    }

    @Override
    public void visit(PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            var brightness = 0.1;
            var normal = polygon.normal;
            normal.normalize();
            for (var light : lights) {
                brightness = Math.max(brightness, 0.1 + -0.9 * normal.scalarMultiply(light.getDirection()));
            }
            polygon.color.setBrightness(brightness);
        }
    }
}
