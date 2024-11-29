package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class ZBufferStrategyCreator implements DrawStrategyCreator {
    @Inject
    public ZBufferStrategyCreator() {
    }

    @Override
    public AbstractDrawStrategy create(@NotNull AbstractImage image, @NotNull Color color) {
        return new ZBufferStrategy(image, color);
    }
}
