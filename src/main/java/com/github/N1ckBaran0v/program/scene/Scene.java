package com.github.N1ckBaran0v.program.scene;

public interface Scene extends Iterable<SceneObject> {
    void addObject(String name, SceneObject sceneObject);

    SceneObject createObject(String creatorName);

    SceneObject getObject(String name);

    void removeObject(String name);
}
