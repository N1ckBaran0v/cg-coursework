package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Dot3D;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

public class ZBufferStrategy implements AbstractDrawStrategy {
    private final AbstractImage image;
    private final double[][] buffer;
    private final double xmin, ymin, xmax, ymax;

    public ZBufferStrategy(AbstractImage image) {
        this.image = image;
        buffer = createZBuffer(image);
        var width = image.getWidth();
        var height = image.getHeight();
        xmin = -width / 2;
        xmax = xmin + width;
        ymax = height / 2;
        ymin = ymax - height;
    }

    private double[][] createZBuffer(AbstractImage image) {
        var width = image.getWidth();
        var height = image.getHeight();
        var buffer = new double[width][];
        var background = new Color(0, 255, 255);
        for (var i = 0; i < width; ++i) {
            buffer[i] = new double[height];
            background.setBrightness(1 - (float) i / width);
            var color = background.mix(0);
            for (var j = 0; j < height; ++j) {
                image.setPixel(i, j, color);
                buffer[i][j] = Double.MAX_VALUE;
            }
        }
        return buffer;
    }

    @Override
    public void draw(Dot3D d1, Dot3D d2, Dot3D d3, Color color) {
        var x0 = Math.max(xmin, Math.floor(Math.min(d1.x, Math.min(d2.x, d3.x))));
        var x1 = Math.min(xmax, Math.ceil(Math.max(d1.x, Math.max(d2.x, d3.x))));
        var y0 = Math.max(ymin, Math.floor(Math.min(d1.y, Math.min(d2.y, d3.y))));
        var y1 = Math.min(ymax, Math.ceil(Math.max(d1.y, Math.max(d2.y, d3.y))));
        var divider = (d1.x - d2.x) * (d2.y - d3.y) - (d2.x - d3.x) * (d1.y - d2.y);
        var i = (int) (x0 - xmin);
        var j0 = (int) (ymax - y0);
        for (var x = x0; x <= x1; ++x, ++i) {
            var j = j0;
            for (var y = y0; y <= y1; ++y, --j) {
                var k1 = (x - d2.x) * (y - d3.y) - (x - d3.x) * (y - d2.y);
                var k2 = (x - d3.x) * (y - d1.y) - (x - d1.x) * (y - d3.y);
                var k3 = (x - d1.x) * (y - d2.y) - (x - d2.x) * (y - d1.y);
                if (k1 >= 0 && k1 <= divider && k2 >= 0 && k2 <= divider && k3 >= 0 && k3 <= divider) {
                    var z = (d1.z * k1 + d2.z * k2 + d3.z * k3) / divider;
                    if (buffer[i][j] > z) {
                        buffer[i][j] = z;
                        image.setPixel(i, j, color.mix(image.getPixel(i, j)));
                    }
                }
            }
        }
    }
}
