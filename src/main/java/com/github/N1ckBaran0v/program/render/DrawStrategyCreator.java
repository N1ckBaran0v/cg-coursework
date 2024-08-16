package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

public interface DrawStrategyCreator {
    AbstractDrawStrategy create(AbstractImage image, Color color);
}
