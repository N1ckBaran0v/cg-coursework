package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.math.DrawVector;
import org.jetbrains.annotations.NotNull;

public interface AbstractDrawStrategy {
    void draw(@NotNull DrawVector d1, @NotNull DrawVector d2, @NotNull DrawVector d3, @NotNull Color color);
}
