package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Vector4D;
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
            calculateBrightness(polygon.color, polygon.normal);
        }
    }

    public void calculateBrightness(Color color, Vector4D normal) {
        var brightness = 0.1;
        for (var light : lights) {
            brightness = Math.max(brightness, 0.1 + -0.9 * normal.scalarMultiply(light.getDirection()));
        }
        color.setBrightness(brightness);
    }
}
