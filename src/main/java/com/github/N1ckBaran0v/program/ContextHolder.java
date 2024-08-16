package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.render.RenderModule;
import com.github.N1ckBaran0v.program.guiAdapters.DrawFactoryModule;
import com.github.N1ckBaran0v.program.scene.SceneModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {RenderModule.class, SceneModule.class, DrawFactoryModule.class})
interface ContextHolder {
    Context getContext();
}
