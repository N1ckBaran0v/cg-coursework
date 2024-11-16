package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.render.Render;
import com.github.N1ckBaran0v.program.scene.Scene;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class Context_Factory implements Factory<Context> {
  private final Provider<Scene> sceneProvider;

  private final Provider<Render> renderProvider;

  public Context_Factory(Provider<Scene> sceneProvider, Provider<Render> renderProvider) {
    this.sceneProvider = sceneProvider;
    this.renderProvider = renderProvider;
  }

  @Override
  public Context get() {
    return newInstance(sceneProvider.get(), renderProvider.get());
  }

  public static Context_Factory create(Provider<Scene> sceneProvider,
      Provider<Render> renderProvider) {
    return new Context_Factory(sceneProvider, renderProvider);
  }

  public static Context newInstance(Scene scene, Render render) {
    return new Context(scene, render);
  }
}
