package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

import javax.inject.Inject;

public class ZBufferStrategyCreator implements DrawStrategyCreator {
    @Inject
    public ZBufferStrategyCreator() {
    }

    @Override
    public AbstractDrawStrategy create(AbstractImage image, Color color) {
        return new ZBufferStrategy(image, color);
    }
}
