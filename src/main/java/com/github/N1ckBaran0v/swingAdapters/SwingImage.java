package com.github.N1ckBaran0v.swingAdapters;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingImage implements AbstractImage {
    private final BufferedImage image;

    public SwingImage(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void setPixel(int i, int j, int color) {
        image.setRGB(i, j, color);
    }

    @Override
    public int getPixel(int i, int j) {
        return image.getRGB(i, j);
    }

    public Image getImage() {
        return image;
    }
}
