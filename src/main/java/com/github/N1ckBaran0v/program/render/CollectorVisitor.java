package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.math.PolygonVector;
import com.github.N1ckBaran0v.program.scene.FarLight;
import com.github.N1ckBaran0v.program.scene.PolygonalModel;
import com.github.N1ckBaran0v.program.scene.SceneObjectVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CollectorVisitor implements SceneObjectVisitor {
    private final Set<PolygonVector> dots = new HashSet<>();
    private List<FarLight> lights = new ArrayList<>();

    @Override
    public void visit(@NotNull PolygonalModel polygonalModel) {
        for (var polygon : polygonalModel) {
            for (var dot : polygon) {
                dots.add(dot);
            }
        }
    }

    @Override
    public void visit(@NotNull FarLight light) {
        lights.add(light);
    }

    public List<FarLight> getLights() {
        return lights;
    }

    public Set<PolygonVector> getDots() {
        return dots;
    }
}
