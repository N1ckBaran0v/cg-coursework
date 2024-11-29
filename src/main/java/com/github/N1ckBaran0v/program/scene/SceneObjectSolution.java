package com.github.N1ckBaran0v.program.scene;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class SceneObjectSolution {
    private Map<String, SceneObjectCreator> creatorMap = new HashMap<>();

    public SceneObjectSolution() {
        creatorMap.put("Camera", new CameraCreator());
        creatorMap.put("FarLight", new FarLightCreator());
    }

    public SceneObjectCreator getCreator(@NotNull String name) {
        return creatorMap.get(name);
    }
}
