package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

public interface DrawStrategyCreator {
    AbstractDrawStrategy create(AbstractImage image);
}
