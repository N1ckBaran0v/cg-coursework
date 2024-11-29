package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.math.Polygon;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Landscape extends PolygonalModel implements Serializable {
    private List<List<Double>> inputHeightsMap;
    private int sideSize, squareSize, step;
    private transient List<Polygon> polygons = new ArrayList<>();

    @Override
    public @NotNull Iterator<Polygon> iterator() {
        return polygons.iterator();
    }

    public @NotNull List<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(@NotNull List<Polygon> polygons) {
        this.polygons = polygons;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getSideSize() {
        return sideSize;
    }

    public void setSideSize(int sideSize) {
        this.sideSize = sideSize;
    }

    public @NotNull List<List<Double>> getInputHeightsMap() {
        return inputHeightsMap;
    }

    public void setInputHeightsMap(@NotNull List<List<Double>> inputHeightsMap) {
        this.inputHeightsMap = inputHeightsMap;
    }
}
