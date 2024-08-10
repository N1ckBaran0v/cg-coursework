package com.github.N1ckBaran0v.program.scene;

import java.util.HashMap;
import java.util.Map;

class SceneObjectSolution {
    private Map<String, SceneObjectCreator> creatorMap = new HashMap<>();

    public SceneObjectSolution() {
        creatorMap.put("Camera", new CameraCreator());
        creatorMap.put("ConvexModel", new ConvexModelCreator());
    }

    public SceneObjectCreator getCreator(String name) {
        return creatorMap.get(name);
    }
}
