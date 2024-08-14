package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.containers.HashMap2D;
import com.github.N1ckBaran0v.program.containers.Map2D;

import java.util.List;

class RegenerateThread extends Thread {
    private final Generator generator;
    private final List<Double> listX, listZ;
    private final long sideSize, maxChunks;
    private final Map2D<Long, Chunk> chunks;
    private final Landscape landscape;

    public RegenerateThread(Generator generator, List<Double> x, List<Double> z, long sideSize, long maxChunks,
                     Map2D<Long, Chunk> chunks, Landscape landscape) {
        this.generator = generator;
        this.listX = x;
        this.listZ = z;
        this.sideSize = sideSize;
        this.maxChunks = maxChunks;
        this.chunks = chunks;
        this.landscape = landscape;
    }

    @Override
    public void run() {
        generator.setGenerates(true);
        var result = new HashMap2D<Long, Chunk>();
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
//        for (var trio : result) {
//            chunks.remove(trio.first, trio.second);
//        }
//        for (var trio : chunks) {
//            if (interrupted()) {
//                return;
//            }
//            generator.removeChunk(result, trio.first, trio.second);
//        }
        if (interrupted()) {
            return;
        }
        landscape.setChunks(result);
        generator.setGenerates(false);
    }
}
