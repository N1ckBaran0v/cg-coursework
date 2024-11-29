package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GenerateLandscapeCommand implements Command {
    private final List<List<Double>> heights;
    private final int sideSize, squareSize, step;

    public GenerateLandscapeCommand(@NotNull List<List<Double>> heights, int sideSize, int squareSize, int step) {
        this.heights = heights;
        this.sideSize = sideSize;
        this.squareSize = squareSize;
        this.step = step;
    }

    @Override
    public void execute(@NotNull Context context) {
        new Generator(context.getLandscape(), heights, sideSize, squareSize, step).start();
        var pos = sideSize / 2;
        var visitor = new SetPositionVisitor(pos, heights.get(heights.size() >> 1).get(heights.size() >> 1) + 500, pos);
        for (var obj : context.getScene()) {
            obj.accept(visitor);
        }
    }
}
