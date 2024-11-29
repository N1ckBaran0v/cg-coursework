package com.github.N1ckBaran0v.program.render;

import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface RenderModule {
    @Binds
    @Singleton
    Render bindRender(RenderImplementation renderImplementation);

    @Binds
    DrawStrategyCreator bindDrawStrategyCreator(ZBufferStrategyCreator zBufferStrategyCreator);
}
