package com.github.N1ckBaran0v.program.guiAdapters;

public interface AbstractDrawFactory {
    AbstractImage getImage(String cameraName);

    AbstractGraphics getGraphics(String cameraName);
}
