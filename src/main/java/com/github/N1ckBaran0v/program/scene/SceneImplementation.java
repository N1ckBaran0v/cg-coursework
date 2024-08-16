package com.github.N1ckBaran0v.program.scene;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SceneImplementation implements Scene {
    private final Map<String, SceneObject> objects = new HashMap<>();
    private final SceneObjectSolution solution = new SceneObjectSolution();

    @Inject
    public SceneImplementation() {
        addObject("Landscape", LandscapeHolder.getInstance());
    }

    @Override
    public void addObject(String name, SceneObject sceneObject) {
        if (objects.containsKey(name)) {
            throw new RuntimeException("Key " + name + " already exists");
        }
        objects.put(name, sceneObject);
    }

    @Override
    public SceneObject createObject(String creatorName) {
        var creator = solution.getCreator(creatorName);
        return creator.create();
    }

    @Override
    public SceneObject getObject(String name) {
        return objects.get(name);
    }

    @Override
    public void removeObject(String name) {
        objects.remove(name);
    }

    @Override
    public Iterator<SceneObject> iterator() {
        return objects.values().iterator();
    }
}
