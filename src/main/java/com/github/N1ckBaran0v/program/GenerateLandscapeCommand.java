package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.scene.LandscapeHolder;

public class GenerateLandscapeCommand implements Command {
    private final long seed;
    private final double minHeight, maxHeight;
    private final long sideSize, step, maxChunks;

    public GenerateLandscapeCommand(long seed, double minHeight, double maxHeight, long sideSize, long step,
                                    long maxChunks) {
        this.seed = seed;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.sideSize = sideSize;
        this.step = step;
        this.maxChunks = maxChunks;
    }

    @Override
    public void execute(Context context) {
        LandscapeHolder.setParams(seed, minHeight, maxHeight, sideSize, step, maxChunks);
        var pos = sideSize / 2;
        var visitor = new SetPositionVisitor(pos, maxHeight + 256, pos, sideSize * maxChunks);
        for (var obj : context.getScene()) {
            obj.accept(visitor);
        }
    }
}
