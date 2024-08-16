package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.HashMap2D;

import java.util.List;

class RegenerateThread extends Thread {
    private final Generator generator;
    private final List<Double> listX, listZ;
    private final Landscape landscape;

    public RegenerateThread(Generator generator, List<Double> x, List<Double> z, Landscape landscape) {
        this.generator = generator;
        this.listX = x;
        this.listZ = z;
        this.landscape = landscape;
    }

    @Override
    public void run() {
        generator.setGenerates(true);
        var chunks = new HashMap2D<>(landscape.getChunks());
        var result = new HashMap2D<Long, Chunk>();
        var sideSize = landscape.getSideSize();
        var maxChunks = landscape.getMaxChunks();
        for (var k = 0; k < listX.size(); ++k) {
            var x = (long) Math.floor(listX.get(k) / sideSize);
            var z = (long) Math.floor(listZ.get(k) / sideSize);
            for (var i = x - maxChunks; i <= x + maxChunks; ++i) {
                for (var j = z - maxChunks; j <= z + maxChunks; ++j) {
                    if (interrupted()) {
                        return;
                    }
                    if (!chunks.contains(i, j)) {
                        chunks.put(i, j, generator.generateChunk(i, j));
                    }
                    result.put(i, j, chunks.get(i, j));
                }
            }
        }
        for (var trio : chunks) {
            if (interrupted()) {
                return;
            }
            if (!result.contains(trio.first, trio.second)) {
                generator.removeChunk(chunks, trio.first, trio.second);
            }
        }
        if (interrupted()) {
            return;
        }
        landscape.setChunks(result);
        landscape.setNeedRecalculate(true);
        generator.setGenerates(false);
    }
}
