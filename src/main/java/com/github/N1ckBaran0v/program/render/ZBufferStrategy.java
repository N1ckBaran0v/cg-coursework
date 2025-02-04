package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.math.DrawVector;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;
import org.jetbrains.annotations.NotNull;

class ZBufferStrategy implements AbstractDrawStrategy {
    private final AbstractImage image;
    private final double[][] buffer;
    private final int xMin, yMin, xMax, yMax;

    public ZBufferStrategy(@NotNull AbstractImage image, @NotNull Color background) {
        this.image = image;
        buffer = createZBuffer(image, background);
        var width = image.getWidth();
        var height = image.getHeight();
        xMin = 0;
        xMax = width - 1;
        yMax = height - 1;
        yMin = 0;
    }

    private double[][] createZBuffer(@NotNull AbstractImage image, @NotNull Color background) {
        var width = image.getWidth();
        var height = image.getHeight();
        var buffer = new double[width][height];
        var color = background.getRGBWithBrightness();
        for (var i = 0; i < width; ++i) {
            for (var j = 0; j < height; ++j) {
                image.setPixel(i, j, color);
                buffer[i][j] = 1.001;
            }
        }
        return buffer;
    }

    @Override
    public void draw(@NotNull DrawVector d1, @NotNull DrawVector d2, @NotNull DrawVector d3, @NotNull Color color) {
        var x0 = Math.max(xMin, (int) Math.floor(Math.min(d1.x, Math.min(d2.x, d3.x))));
        var x1 = Math.min(xMax, (int) Math.ceil(Math.max(d1.x, Math.max(d2.x, d3.x))));
        var y0 = Math.max(yMin, (int) Math.floor(Math.min(d1.y, Math.min(d2.y, d3.y))));
        var y1 = Math.min(yMax, (int) Math.ceil(Math.max(d1.y, Math.max(d2.y, d3.y))));
        var divider = (d1.x - d2.x) * (d2.y - d3.y) - (d2.x - d3.x) * (d1.y - d2.y);
        for (var x = x0; x <= x1; ++x) {
            for (var y = y0; y <= y1; ++y) {
                var k1 = ((x - d2.x) * (y - d3.y) - (x - d3.x) * (y - d2.y)) / divider;
                var k2 = ((x - d3.x) * (y - d1.y) - (x - d1.x) * (y - d3.y)) / divider;
                var k3 = ((x - d1.x) * (y - d2.y) - (x - d2.x) * (y - d1.y)) / divider;
                if (k1 >= 0 && k1 <= 1 && k2 >= 0 && k2 <= 1 && k3 >= 0 && k3 <= 1) {
                    var z = d1.z * k1 + d2.z * k2 + d3.z * k3;
                    if (buffer[x][y] > z) {
                        buffer[x][y] = z;
                        if (z > 0.9) {
                            color.setAlpha((int) ((1 - z) * 2550));
                        }
                        var brightness = d1.brightness * k1 + d2.brightness * k2 + d3.brightness * k3;
                        color.setBrightness(brightness);
                        image.setPixel(x, y, color.getRGBWithBrightness());
                        color.setAlpha(255);
                    }
                }
            }
        }
    }
}
