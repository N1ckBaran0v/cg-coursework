package com.github.N1ckBaran0v.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

class CanvasPanel extends JPanel {
    public CanvasPanel() {
    }

    @Override
    public void paint(Graphics g) {
        var gen = new Random();
        var img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (var i = 0; i < getWidth(); ++i) {
            for (var j = 0; j < getHeight(); ++j) {
                img.setRGB(i, j, gen.nextInt(256) << 16 | gen.nextInt(256) << 8 | gen.nextInt(256));
            }
        }
        g.drawImage(img, 0, 0, this);
    }
}
