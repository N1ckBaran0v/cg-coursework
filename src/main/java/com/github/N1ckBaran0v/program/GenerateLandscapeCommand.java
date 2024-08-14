package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.LandscapeHolder;

public class GenerateLandscapeCommand implements Command {
    private final long seed;
    private final double minHeight, maxHeight;
    private final long step, maxChunks;

    public GenerateLandscapeCommand(long seed, double minHeight, double maxHeight, long step, long maxChunks) {
        this.seed = seed;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.step = step;
        this.maxChunks = maxChunks;
    }

    @Override
    public void execute(Context context) {
        LandscapeHolder.setParams(seed, minHeight, maxHeight, step, maxChunks);
        var landscape = LandscapeHolder.getInstance();
        var visitor = new SetPositionVisitor(landscape.SIDE_SIZE / 2, maxHeight + 256,
                landscape.SIDE_SIZE / 2, landscape.SIDE_SIZE * maxChunks);
        for (var obj : context.getScene()) {
            obj.accept(visitor);
        }
    }
}
