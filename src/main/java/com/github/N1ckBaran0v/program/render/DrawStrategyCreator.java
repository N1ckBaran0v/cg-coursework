package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import org.jetbrains.annotations.NotNull;

public interface DrawStrategyCreator {
    AbstractDrawStrategy create(@NotNull AbstractImage image, @NotNull Color color);
}
