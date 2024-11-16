package com.github.N1ckBaran0v.program.guiAdapters;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DrawFactoryModule_ProvideAbstractDrawFactoryFactory implements Factory<AbstractDrawFactory> {
  @Override
  public AbstractDrawFactory get() {
    return provideAbstractDrawFactory();
  }

  public static DrawFactoryModule_ProvideAbstractDrawFactoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AbstractDrawFactory provideAbstractDrawFactory() {
    return Preconditions.checkNotNullFromProvides(DrawFactoryModule.provideAbstractDrawFactory());
  }

  private static final class InstanceHolder {
    private static final DrawFactoryModule_ProvideAbstractDrawFactoryFactory INSTANCE = new DrawFactoryModule_ProvideAbstractDrawFactoryFactory();
  }
}
