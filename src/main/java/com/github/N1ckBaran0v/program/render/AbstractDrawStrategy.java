package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.DrawVector;

public interface AbstractDrawStrategy {
    void draw(DrawVector d1, DrawVector d2, DrawVector d3, Color color);
}
