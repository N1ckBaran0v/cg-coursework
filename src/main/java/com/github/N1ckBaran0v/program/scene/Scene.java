package com.github.N1ckBaran0v.program.scene;

import org.jetbrains.annotations.NotNull;

public interface Scene extends Iterable<SceneObject> {
    void addObject(@NotNull String name, @NotNull SceneObject sceneObject);

    SceneObject createObject(@NotNull String creatorName);

    SceneObject getObject(@NotNull String name);

    void removeObject(@NotNull String name);
}
