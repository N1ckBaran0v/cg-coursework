package com.github.N1ckBaran0v.program.geometry;

public class Color {
    private int argb;
    private float brightness;
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 255;
    private static final float MIN_BRIGHTNESS = 0;
    private static final float MAX_BRIGHTNESS = 255;

    public Color() {
        this.argb = ~MIN_VAL;
        this.brightness = MAX_BRIGHTNESS;
    }

    public Color(Color other) {
        this.argb = other.argb;
        this.brightness = other.brightness;
    }

    public Color(int r, int g, int b) {
        r = checkParam(r);
        g = checkParam(g);
        b = checkParam(b);
        this.argb = MAX_VAL << 24 | r << 16 | g << 8 | b;
        this.brightness = 1f;
    }

    public Color(int r, int g, int b, float brightness) {
        r = checkParam(r);
        g = checkParam(g);
        b = checkParam(b);
        this.argb = MAX_VAL << 24 | r << 16 | g << 8 | b;
        setBrightness(brightness);
    }

    public Color(int r, int g, int b, int alpha) {
        r = checkParam(r);
        g = checkParam(g);
        b = checkParam(b);
        alpha = checkParam(alpha);
        this.argb = alpha << 24 | r << 16 | g << 8 | b;
        this.brightness = 1f;
    }

    public Color(int r, int g, int b, int alpha, float brightness) {
        r = checkParam(r);
        g = checkParam(g);
        b = checkParam(b);
        alpha = checkParam(alpha);
        this.argb = alpha << 24 | r << 16 | g << 8 | b;
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

    public void setBrightness(float brightness) {
        if (brightness > MAX_BRIGHTNESS) {
            this.brightness = MAX_BRIGHTNESS;
        } else if (brightness < MIN_BRIGHTNESS) {
            this.brightness = MIN_BRIGHTNESS;
        } else {
            this.brightness = brightness;
        }
    }

    public int getRGB() {
        return argb;
    }

    public float getBrightness() {
        return brightness;
    }

    private void setAlpha(int alpha) {
        alpha = checkParam(alpha);
        argb = alpha << 24 | (argb & 0xffffff);
    }

    public int mix(int other) {
        var opacity = (double) ((argb >> 24) & 0xff) / MAX_VAL;
        var transparency = 1 - opacity;
        var r0 = (int) (((argb >> 16) & 0xff) * brightness * opacity);
        var g0 = (int) (((argb >> 8) & 0xff) * brightness * opacity);
        var b0 = (int) ((argb & 0xff) * brightness * opacity);
        var r1 = (int) (((other >> 16) & 0xff) * transparency);
        var g1 = (int) (((other >> 8) & 0xff) * transparency);
        var b1 = (int) ((other & 0xff) * transparency);
        return MAX_VAL << 24 | (r0 + r1) << 16 | (g0 + g1) << 16 | (b0 + b1);
    }
}
