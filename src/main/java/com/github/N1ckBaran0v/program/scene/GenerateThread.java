package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.HashMap2D;

class GenerateThread extends Thread {
    private final Generator generator;
    private final Landscape landscape;

    public GenerateThread(Generator generator, Landscape landscape) {
        this.generator = generator;
        this.landscape = landscape;
    }

    @Override
    public void run() {
        generator.setGenerates(true);
        if (interrupted()) {
            return;
        }
        generator.setParams(landscape);
        var map = new HashMap2D<Long, Chunk>();
        var maxChunks = landscape.getMaxChunks();
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
