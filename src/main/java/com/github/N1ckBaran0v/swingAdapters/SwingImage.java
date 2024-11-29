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
        image.setRGB(i, j, mix(image.getRGB(i, j), color));
    }

    @Override
    public int getPixel(int i, int j) {
        return image.getRGB(i, j);
    }

    public Image getImage() {
        return image;
    }

    private int mix(int currColor, int newColor) {
        var opacity = (double) (((newColor >> 24) + 256) % 256) / 255;
        var transparency = 1 - opacity;
        var r0 = (int) (((newColor >> 16) & 0xff) * opacity);
        var g0 = (int) (((newColor >> 8) & 0xff) * opacity);
        var b0 = (int) ((newColor & 0xff) * opacity);
        var r1 = (int) (((currColor >> 16) & 0xff) * transparency);
        var g1 = (int) (((currColor >> 8) & 0xff) * transparency);
        var b1 = (int) ((currColor & 0xff) * transparency);
        return 255 << 24 | (r0 + r1) << 16 | (g0 + g1) << 8 | (b0 + b1);
    }
}
