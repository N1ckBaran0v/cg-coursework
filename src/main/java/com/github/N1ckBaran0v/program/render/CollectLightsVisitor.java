package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.scene.Composite;
import com.github.N1ckBaran0v.program.scene.FarLight;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;

import java.util.ArrayList;
import java.util.List;

class CollectLightsVisitor implements SceneObjectVisitor {
    private List<FarLight> lights = new ArrayList<>();

    @Override
    public void visit(Composite composite) {
    }

    @Override
    public void visit(FarLight light) {
        lights.add(light);
    }

    public List<FarLight> getLights() {
        return lights;
    }
}
