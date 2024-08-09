package com.github.N1ckBaran0v.program.guiAdapters;

public interface AbstractImage {
    int getWidth();

    int getHeight();

    void setPixel(int i, int j, int color);

    int getPixel(int i, int j);
}
