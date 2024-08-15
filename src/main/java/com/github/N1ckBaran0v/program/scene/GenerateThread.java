package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.HashMap2D;

class GenerateThread extends Thread {
    private final Generator generator;
    private final long seed;
    private final double minHeight, maxHeight;
    private final long sideSize, step, maxChunks;
    private final Landscape landscape;

    public GenerateThread(Generator generator, long seed, double minHeight, double maxHeight, long sideSize, long step,
                          long maxChunks, Landscape landscape) {
        this.generator = generator;
        this.seed = seed;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.sideSize = sideSize;
        this.step = step;
        this.maxChunks = maxChunks;
        this.landscape = landscape;
    }

    @Override
    public void run() {
        generator.setGenerates(true);
        if (interrupted()) {
            return;
        }
        generator.setParams(seed, minHeight, maxHeight, sideSize, step);
        var map = new HashMap2D<Long, Chunk>();
        for (var i = -maxChunks; i <= maxChunks; ++i) {
            for (var j = -maxChunks; j <= maxChunks; ++j) {
                if (interrupted()) {
                    return;
                }
                map.put(i, j, generator.generateChunk(i, j));
            }
        }
        if (interrupted()) {
            return;
        }
        landscape.setChunks(map);
        landscape.setNeedRecalculate(true);
        generator.setGenerates(false);
    }
}
