package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.scene.Scene;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class RenderImplementation_Factory implements Factory<RenderImplementation> {
  private final Provider<Scene> sceneProvider;

  private final Provider<AbstractDrawFactory> abstractDrawFactoryProvider;

  private final Provider<DrawStrategyCreator> drawStrategyCreatorProvider;

  public RenderImplementation_Factory(Provider<Scene> sceneProvider,
      Provider<AbstractDrawFactory> abstractDrawFactoryProvider,
      Provider<DrawStrategyCreator> drawStrategyCreatorProvider) {
    this.sceneProvider = sceneProvider;
    this.abstractDrawFactoryProvider = abstractDrawFactoryProvider;
    this.drawStrategyCreatorProvider = drawStrategyCreatorProvider;
  }

  @Override
  public RenderImplementation get() {
    return newInstance(sceneProvider.get(), abstractDrawFactoryProvider.get(), drawStrategyCreatorProvider.get());
  }

  public static RenderImplementation_Factory create(Provider<Scene> sceneProvider,
      Provider<AbstractDrawFactory> abstractDrawFactoryProvider,
      Provider<DrawStrategyCreator> drawStrategyCreatorProvider) {
    return new RenderImplementation_Factory(sceneProvider, abstractDrawFactoryProvider, drawStrategyCreatorProvider);
  }

  public static RenderImplementation newInstance(Scene scene,
      AbstractDrawFactory abstractDrawFactory, DrawStrategyCreator drawStrategyCreator) {
    return new RenderImplementation(scene, abstractDrawFactory, drawStrategyCreator);
  }
}
