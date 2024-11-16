package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.guiAdapters.DrawFactoryModule_ProvideAbstractDrawFactoryFactory;
import com.github.N1ckBaran0v.program.render.Render;
import com.github.N1ckBaran0v.program.render.RenderImplementation;
import com.github.N1ckBaran0v.program.render.RenderImplementation_Factory;
import com.github.N1ckBaran0v.program.render.ZBufferStrategyCreator_Factory;
import com.github.N1ckBaran0v.program.scene.Scene;
import com.github.N1ckBaran0v.program.scene.SceneImplementation_Factory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;
import javax.annotation.processing.Generated;

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
final class DaggerContextHolder {
  private DaggerContextHolder() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ContextHolder create() {
    return new Builder().build();
  }

  static final class Builder {
    private Builder() {
    }

    public ContextHolder build() {
      return new ContextHolderImpl();
    }
  }

  private static final class ContextHolderImpl implements ContextHolder {
    private final ContextHolderImpl contextHolderImpl = this;

    private Provider<Scene> bindSceneProvider;

    private Provider<RenderImplementation> renderImplementationProvider;

    private Provider<Render> bindArtistProvider;

    private Provider<Context> contextProvider;

    private ContextHolderImpl() {

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.bindSceneProvider = DoubleCheck.provider((Provider) SceneImplementation_Factory.create());
      this.renderImplementationProvider = RenderImplementation_Factory.create(bindSceneProvider, DrawFactoryModule_ProvideAbstractDrawFactoryFactory.create(), ((Provider) ZBufferStrategyCreator_Factory.create()));
      this.bindArtistProvider = DoubleCheck.provider((Provider) renderImplementationProvider);
      this.contextProvider = DoubleCheck.provider(Context_Factory.create(bindSceneProvider, bindArtistProvider));
    }

    @Override
    public Context getContext() {
      return contextProvider.get();
    }
  }
}
