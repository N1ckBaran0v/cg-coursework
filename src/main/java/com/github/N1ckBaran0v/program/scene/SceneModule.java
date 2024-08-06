package com.github.N1ckBaran0v.program.scene;

import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface SceneModule {
    @Binds
    @Singleton
    Scene bindScene(SceneImplementation sceneImplementation);
}
