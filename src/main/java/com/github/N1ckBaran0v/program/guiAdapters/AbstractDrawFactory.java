package com.github.N1ckBaran0v.program.guiAdapters;

import org.jetbrains.annotations.NotNull;

public interface AbstractDrawFactory {
    AbstractImage getImage(@NotNull String cameraName);

    AbstractGraphics getGraphics(@NotNull String cameraName);
}
