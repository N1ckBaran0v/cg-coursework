package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.geometry.Polygon3D;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

import java.util.List;

public interface AbstractDrawStrategy {
    void draw(List<Polygon3D> polygon3DList);
}
