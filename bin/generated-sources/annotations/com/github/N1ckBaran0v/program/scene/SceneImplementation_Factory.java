package com.github.N1ckBaran0v.program.scene;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SceneImplementation_Factory implements Factory<SceneImplementation> {
  @Override
  public SceneImplementation get() {
    return newInstance();
  }

  public static SceneImplementation_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SceneImplementation newInstance() {
    return new SceneImplementation();
  }

  private static final class InstanceHolder {
    private static final SceneImplementation_Factory INSTANCE = new SceneImplementation_Factory();
  }
}
