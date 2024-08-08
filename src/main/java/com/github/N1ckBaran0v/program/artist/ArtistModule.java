package com.github.N1ckBaran0v.program.artist;

import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface ArtistModule {
    @Binds
    @Singleton
    Artist bindArtist(ArtistImplementation artistImplementation);

    @Binds
    DrawStrategyCreator bindDrawStrategyCreator(ZBufferStrategyCreator zBufferStrategyCreator);
}
