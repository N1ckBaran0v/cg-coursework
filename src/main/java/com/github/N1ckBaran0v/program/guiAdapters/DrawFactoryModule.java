package com.github.N1ckBaran0v.program.guiAdapters;

import dagger.Module;
import dagger.Provides;

@Module
public class DrawFactoryModule {
    private static AbstractDrawFactory abstractDrawFactory;

    @Provides
    public static AbstractDrawFactory provideAbstractDrawFactory() {
        return abstractDrawFactory;
    }

    public static void setAbstractDrawFactory(AbstractDrawFactory abstractDrawFactory) {
        DrawFactoryModule.abstractDrawFactory = abstractDrawFactory;
    }
}
