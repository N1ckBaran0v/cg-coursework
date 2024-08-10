package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.geometry.Color;

public interface AbstractDrawStrategy {
    void draw(Dot3D d1, Dot3D d2, Dot3D d3, Color color);
}
