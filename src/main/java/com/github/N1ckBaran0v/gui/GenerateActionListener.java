package com.github.N1ckBaran0v.gui;

import com.github.N1ckBaran0v.program.Facade;
import com.github.N1ckBaran0v.program.GenerateLandscapeCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class GenerateActionListener implements ActionListener {
    private int sideSize, squareSize, step;
    private List<List<Double>> heights;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Facade.execute(new GenerateLandscapeCommand(heights, sideSize, squareSize, step));
    }

    public void setParams(int sideSize, int squareSize, int step, double defaultValue) {
        if (sideSize == 0 || squareSize == 0 || step == 0 || sideSize % squareSize != 0 || squareSize % step != 0) {
            throw new IllegalArgumentException();
        }
        this.sideSize = sideSize;
        this.squareSize = squareSize;
        this.step = step;
        var len = sideSize / squareSize + 1;
        heights = new ArrayList<>(len);
        for (var i = 0; i < len; ++i) {
            heights.add(new ArrayList<>(len));
            for (var j = 0; j < len; ++j) {
                heights.get(i).add(defaultValue);
            }
        }
    }

    public double get(int x, int y) {
        return heights.get(x / squareSize).get(y / squareSize);
    }

    public void set(int x, int y, double value) {
        heights.get(x / squareSize).set(y / squareSize, value);
    }

    public int getSquareSize() {
        return squareSize;
    }

    public int getSideSize() {
        return sideSize;
    }
}
