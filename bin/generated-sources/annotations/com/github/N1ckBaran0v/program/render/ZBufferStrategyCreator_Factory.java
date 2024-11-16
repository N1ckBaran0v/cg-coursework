package com.github.N1ckBaran0v.program.render;

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
public final class ZBufferStrategyCreator_Factory implements Factory<ZBufferStrategyCreator> {
  @Override
  public ZBufferStrategyCreator get() {
    return newInstance();
  }

  public static ZBufferStrategyCreator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ZBufferStrategyCreator newInstance() {
    return new ZBufferStrategyCreator();
  }

  private static final class InstanceHolder {
    private static final ZBufferStrategyCreator_Factory INSTANCE = new ZBufferStrategyCreator_Factory();
  }
}
