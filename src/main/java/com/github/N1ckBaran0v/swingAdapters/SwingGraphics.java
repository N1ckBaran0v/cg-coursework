package com.github.N1ckBaran0v.swingAdapters;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractGraphics;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

import java.awt.*;
import java.awt.image.ImageObserver;

public class SwingGraphics implements AbstractGraphics {
    private final Graphics graphics;
    private final ImageObserver imageObserver;

    public SwingGraphics(Graphics graphics, ImageObserver imageObserver) {
        this.graphics = graphics;
        this.imageObserver = imageObserver;
    }

    @Override
    public void draw(AbstractImage image) {
        graphics.drawImage(((SwingImage) image).getImage(), 0, 0, imageObserver);
    }
}
