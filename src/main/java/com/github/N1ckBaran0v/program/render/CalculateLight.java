package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Vector4D;
import com.github.N1ckBaran0v.program.scene.FarLight;

import java.util.List;

class CalculateLight {
    private final List<FarLight> lights;

    public CalculateLight(List<FarLight> lights) {
        this.lights = lights;
    }

    public double calculateBrightness(Vector4D normal) {
        var brightness = 0.1;
        normal.normalize();
        for (var light : lights) {
            brightness = Math.max(brightness, 0.1 + -0.9 * normal.scalarMultiply(light.getDirection()));
        }
        return brightness;
    }
}
