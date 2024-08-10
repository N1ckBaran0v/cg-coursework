package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.geometry.Color;
import com.github.N1ckBaran0v.program.geometry.Vector3D;

public interface AbstractDrawStrategy {
    void draw(Vector3D d1, Vector3D d2, Vector3D d3, Color color);
}
