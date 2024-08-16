package com.github.N1ckBaran0v.program.geometry;

public class Color implements Cloneable {
    private final int r;
    private final int g;
    private final int b;
    private int alpha;
    private double brightness;
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 255;
    private static final float MIN_BRIGHTNESS = 0;
    private static final float MAX_BRIGHTNESS = 1;

    public Color() {
        this(MAX_VAL, MAX_VAL, MAX_VAL, MAX_VAL, MAX_BRIGHTNESS);
    }

    public Color(Color other) {
        this(other.r, other.g, other.b, other.alpha, other.brightness);
    }

    public Color(int r, int g, int b) {
        this(r, g, b, MAX_VAL, MAX_BRIGHTNESS);
    }

    public Color(int r, int g, int b, double brightness) {
        this(r, g, b, MAX_VAL, brightness);
    }

    public Color(int r, int g, int b, int alpha) {
        this(r, g, b, alpha, MAX_BRIGHTNESS);
    }

    public Color(int r, int g, int b, int alpha, double brightness) {
        this.r = checkParam(r);
        this.g = checkParam(g);
        this.b = checkParam(b);
        this.alpha = checkParam(alpha);
        setBrightness(brightness);
    }

    private int checkParam(int param) {
        if (param < MIN_VAL) {
            param = MIN_VAL;
        } else if (param > MAX_VAL) {
            param = MAX_VAL;
        }
        return param;
    }

    public void setBrightness(double brightness) {
        if (brightness > MAX_BRIGHTNESS) {
            this.brightness = MAX_BRIGHTNESS;
        } else if (brightness < MIN_BRIGHTNESS) {
            this.brightness = MIN_BRIGHTNESS;
        } else {
            this.brightness = brightness;
        }
    }

    public int getRGB() {
        return alpha << 24 | r << 16 | g << 8 | b;
    }

    public int getRGBWithBrightness() {
        return alpha << 24 | ((int) (r * brightness)) << 16 | ((int) (g * brightness)) << 8 | ((int) (b * brightness));
    }

    public double getBrightness() {
        return brightness;
    }

    public void setAlpha(int alpha) {
        this.alpha = checkParam(alpha);
    }

    public int mix(int other) {
        var opacity = (double) alpha / MAX_VAL;
        var transparency = 1 - opacity;
        var r0 = (int) (r * brightness * opacity);
        var g0 = (int) (g * brightness * opacity);
        var b0 = (int) (b * brightness * opacity);
        var r1 = (int) (((other >> 16) & 0xff) * transparency);
        var g1 = (int) (((other >> 8) & 0xff) * transparency);
        var b1 = (int) ((other & 0xff) * transparency);
        return MAX_VAL << 24 | (r0 + r1) << 16 | (g0 + g1) << 8 | (b0 + b1);
    }

    @Override
    public Color clone() {
        return new Color(r, g, b, alpha, brightness);
    }
}
